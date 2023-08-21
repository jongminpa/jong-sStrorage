async function myName(){
    return "Park";
}

async function showName(){
    const name = await myName();
    console.log(name);
}

console.log(showName());