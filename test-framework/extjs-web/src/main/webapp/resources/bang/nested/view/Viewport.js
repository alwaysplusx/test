/**
 * The main application viewport, which displays the whole application
 * @extends Ext.Viewport
 */
Ext.define('Books.view.Viewport', {
    extend: 'Ext.Viewport',
    layout: 'border',

    initComponent: function () {
        var me = this;

        console.info(">>>>>> viewport init" + me);

        Ext.apply(me, {
            items: [{
                xtype: 'panel',
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