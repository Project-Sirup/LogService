db = new Mongo().getDB("sirupLog");

db.createUser({
    user: "admin",
    pwd: "admin",
    roles: [
        {
            role: "readWrite",
            db: "sirupLog",
        },
    ],
});