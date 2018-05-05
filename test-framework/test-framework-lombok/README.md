## use lombok compile

```
javac -d target/classes -cp src/main/resources/lombok.jar src/main/java/org/harmony/test/framework/lombok/User.java
```

## check .class
```
javap target/classes/org/harmony/test/framework/lombok/User
```
result
```
Compiled from "User.java"
public class org.harmony.test.framework.lombok.User {
  public org.harmony.test.framework.lombok.User();
  public java.lang.String getUsername();
  public java.lang.String getPassword();
  public void setUsername(java.lang.String);
  public void setPassword(java.lang.String);
  public boolean equals(java.lang.Object);
  protected boolean canEqual(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
}
```