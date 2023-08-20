const DB=[];
//예외처리 추가
function saveDB(user){
    const oldDBSize = DB.length+1;
    DB.push(user);
    console.log(`save ${user.name} to DB`);
    return new Promise((resolve, rejiect) => { //callback 대신 Promist 객체 반환
        if (DB.length > oldDBSize){
            resolve(user); // 성공 시 유저 정보 반환
        }else{
            rejiect(new Error("Save DB Eroor!")); // 실패 시 에러발생
        }
    });
}

function sendEmail(user){
    console.log(`email to ${user.email}`);
    return new Promise((resolve) => { //Promise 객체를 변환. 실패처리 없음
        resolve(user);
    });
}

function getResult(user){
    return new Promise((resolve, rejiect) => { //Promise 객체 반환
        resolve(`success register ${user.name}`);// 성공 시 성공 메세지와 유저명 반환
    });
}

function registerByPromise(user){
    //비동기 호출이지만, 순서를 지켜서 실행
    const result = saveDB(user).then(sendEmail).then(getResult).catch(error => new Error(error)).finally(()=>console.log("완료 !"));
    //아직 완료되지 않았으므로 지연(pending) 상태
    console.log(result);
    return result;
}

const myUser = {email: "bjm2582@test.com", password:"1234", name:"jongmin"};
//const result = registerByPromise(myUser);
//결과값이; promise 이므로 then() 메서드에 함수를 넣어서 결괏값을 볼 수 이;ㅆ음
//result.then(console.log);

//동시에 여러 Promise 객체 호출

allResult = Promise.all([saveDB(myUser),sendEmail(myUser),getResult(myUser)]);
allResult.then(console.log);