<#macro field data>
    private ${data.type} ${data.name};
</#macro>

<#macro getter data>
    public ${data.type} get${data.name?cap_first}() {
        return this.${data.name};
    }
</#macro>

<#macro setter data>
    public void set${data.name?cap_first}(${data.type} ${data.name}) {
        this.${data.name} = ${data.name};
    }
</#macro>