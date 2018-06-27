
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;

/**
 * @author wuxii@foxmail.com
 */
public class JavadocTest {

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        for (ClassDoc classDoc : classes) {
            System.out.println(classDoc);
            AnnotationDesc[] annotations = classDoc.annotations();
            for (AnnotationDesc ann : annotations) {
                System.out.println(ann);
            }
            FieldDoc[] fields = classDoc.fields();
            for (FieldDoc field : fields) {
                System.out.println(field);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello);
        test();
    }

    public static void test() {
        String[] docArgs = new String[] { "-private", "-doclet", JavadocTest.class.getName(), "src/main/java/Hello.java" };
        com.sun.tools.javadoc.Main.execute(docArgs);
    }

}
