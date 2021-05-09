import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.lw3d.Lw3dLoader;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Car extends JFrame {
    static SimpleUniverse universe;
    static Scene scene;
    static Map<String, Shape3D> nameMap;
    static BranchGroup root;
    static Canvas3D canvas;

    static TransformGroup wholeCar;
    static Transform3D transform3D;

    public Car() throws IOException {
        configureWindow();
        configureCanvas();
        configureUniverse();
        addModelToUniverse();
        setCarElementsList();
        addAppearance();
        addImageBackground();
        addLightToUniverse();
        addOtherLight();
        ChangeViewAngle();
        root.compile();
        universe.addBranchGraph(root);
    }

    public static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    public static void main(String[] args) {
        try {
            Car window = new Car();
            AnimationCar carMovement = new AnimationCar(wholeCar, transform3D, window);
            window.setVisible(true);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void configureWindow() {
        setTitle("Car Animation");
        setSize(760, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        root = new BranchGroup();
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addModelToUniverse() throws IOException {
        scene = getSceneFromFile("resources/camero-2010-low-poly.obj");
        root = scene.getSceneGroup();
    }

    private void addLightToUniverse() {
        Bounds bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        Color3f color = new Color3f(71 / 255f, 71 / 255f, 71 / 255f);
        Vector3f lightdirection = new Vector3f(5f, -2f, 0.7f);
        DirectionalLight dirlight = new DirectionalLight(color, lightdirection);
        dirlight.setInfluencingBounds(bounds);
        root.addChild(dirlight);
    }

    private void printModelElementsList(Map<String, Shape3D> nameMap) {
        for (String name : nameMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    private void setCarElementsList() {
        nameMap = scene.getNamedObjects();
        //Print elements of your model:
        printModelElementsList(nameMap);

        wholeCar = new TransformGroup();

        transform3D = new Transform3D();
        transform3D.rotY(-Math.PI);
        wholeCar.setTransform(transform3D);
        transform3D.setTranslation(new Vector3f(0.8f, 0f, 0));
        wholeCar.setTransform(transform3D);
        transform3D.setScale(0.8f);
        wholeCar.setTransform(transform3D);

        root.removeChild(nameMap.get("default"));
        wholeCar.addChild(nameMap.get("default"));
        wholeCar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(wholeCar);
    }

    Texture getTexture(String path) {
        TextureLoader textureLoader = new TextureLoader(path, "RPG", canvas);
        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
        return texture;
    }

    Material getMaterial() {
        Material material = new Material();
        material.setAmbientColor(new Color3f(0.6f, 0.6f, 0f));
        material.setSpecularColor(1.f, 1.f, 0.8f);
        material.setDiffuseColor(1.f, 0.4f, 0.1f);

        material.setShininess(0f);
        material.setLightingEnable(true);
        return material;
    }

    private void addAppearance() {
        Appearance carAppearance = new Appearance();
        carAppearance.setTexture(getTexture("resources/camero_map.png"));
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.COMBINE);
        carAppearance.setTextureAttributes(texAttr);
        carAppearance.setMaterial(getMaterial());
        Shape3D car = nameMap.get("default");
        car.setAppearance(carAppearance);
    }

    private void addImageBackground() {
        TextureLoader t = new TextureLoader("resources/way_2.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        Vector3f translationVector = new Vector3f(0F, 1F, 5F);
        vpTranslation.setTranslation(translationVector);
        vpGroup.setTransform(vpTranslation);
    }

    private void addOtherLight() {
        Color3f directionalLightColor = new Color3f(Color.BLACK);
        Color3f ambientLightColor = new Color3f(Color.WHITE);
        Vector3f lightDirection = new Vector3f(0F, 0F, 0F);

        AmbientLight ambientLight = new AmbientLight(ambientLightColor);
        DirectionalLight directionalLight = new DirectionalLight(directionalLightColor, lightDirection);

        Bounds influenceRegion = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        ambientLight.setInfluencingBounds(influenceRegion);
        directionalLight.setInfluencingBounds(influenceRegion);
        root.addChild(ambientLight);
        root.addChild(directionalLight);
    }
}