Ext.define("Q.user.controller.UserController", {
    extend: "Ext.app.Controller",

    models: ["UserModel"],
    stores: ["UserStore"],

    init: function () {
    	var me = this;
        console.info(">>>>> user controller init " + me);
    }
});