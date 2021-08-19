import java.io.InputStream;

public class ResourceLoader {
    public static final String RESOURCE_FOLDER = "/resources";

    public InputStream getResource(String url){
        return ResourceLoader.class.getResourceAsStream(RESOURCE_FOLDER+url);
    }
}
