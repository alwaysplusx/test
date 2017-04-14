package ${package?ensure_ends_with(";")}

<#list imports as import>
import ${import?ensure_ends_with(";")}
</#list>

${comment}
public class ${name} implements Serializable {

    private static final long serialVersionUID = 1L;
    
    <#import "member.macro.ftl" as e>
    
    <#list fields as field>
        <@e.field data=field />
    </#list>
    <#list fields as field>
    
        <@e.getter data=field />
        
        <@e.setter data=field />
    </#list>
    
    
}
