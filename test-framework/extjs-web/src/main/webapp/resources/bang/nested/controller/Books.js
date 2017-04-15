/**
 * Books controller
 * Used to manage books; showing the first book, showing a spefied book, loading books, and showing their
 * related views
 */
Ext.define('Books.controller.Books', {
    extend: 'Ext.app.Controller',
    
    models: ['Book'],
    stores: ['Books'],
    
    init: function() {
        var me = this;
        console.info(">>>>>> controller init" + me);
    }

});