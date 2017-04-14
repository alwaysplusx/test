<#-- 一对多中的一方 -->
<#macro one data>
    @OneToMany(mappedBy = "${data.mappedBy}", cascade = {})
    private ${data.type} ${data.name};
</#macro>

<#-- 一对多中的多方 -->
<#macro many data>
    @ManyToOne()
    private ${data.type} ${data.name};
</#macro>
