Ext.define("Q.user.store.UserStore", {
    extend: "Ext.data.ArrayStore",
    model: "Q.user.model.UserModel",
    data: [
        {
            userId: 1,
            username: "Tom",
            nickname: "Tom",
            password: "123456",
            email: "tom@foxmail.com",
            phoneNumber: "18229404021"
        },
        {
            userId: 2,
            username: "Jerry",
            nickname: "Jerry",
            password: "123456",
            email: "jerry@foxmail.com",
            phoneNumber: "18229404022"
        },
        {
            userId: 3,
            username: "Mary",
            nickname: "Mary",
            password: "123456",
            email: "mary@foxmail.com",
            phoneNumber: "18229404023"
        },
        {
            userId: 4,
            username: "David",
            nickname: "David",
            password: "123456",
            email: "david@foxmail.com",
            phoneNumber: "18229404024"
        }
    ]
});