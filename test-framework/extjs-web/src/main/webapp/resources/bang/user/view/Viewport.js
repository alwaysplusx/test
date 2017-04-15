Ext.define("Q.user.view.Viewport", {
    extend: "Ext.Viewport",
    layout: "border",
    
	initComponent: function(){
		var me = this;
		
		console.info("user viewport init " + me);
		
		Ext.apply(me, {
			items: [{
				xtype: "panel",
				region: "center",
				border: false,
				layout: "border",
				items: [{
                    xtype: "panel",
                    region: "center",
                    title: "Hello"
                }]
			}]
		});
		
		me.callParent(arguments);
	}
});