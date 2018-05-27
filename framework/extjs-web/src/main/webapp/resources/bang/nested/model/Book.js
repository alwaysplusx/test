/**
 * Model for a book
 */
Ext.define('Books.model.Book', {
    extend: 'Ext.data.Model',

    fields: [
        'id',
        'name',
        'author',
        'detail',
        'price',
        'image'
    ]
});
