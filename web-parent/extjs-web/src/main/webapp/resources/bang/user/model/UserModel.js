Ext.define('Q.user.model.UserModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'userId', type: 'int'},
        {name: 'username', type: 'string'},
        {name: 'nickname', type: 'string'},
        {name: 'password', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'phoneNumber', type: 'string'}
    ]
});