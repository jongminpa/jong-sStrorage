const express = require("express");
const app = express();
const port = 3000;

app.get("/",(req,res)=>{
    res.set({"Content-Type":"text/html"});
    res.end("헬로 express");
})

app.listen(port,()=>{
    console.log(`Start Server : use ${port}`);
})