function waitOneSecond(msg){//1초 대기하고 메시지 출력
    return new Promise((resolve,_)=>{
        setTimeout(()=> resolve(`${msg}`),1000);
    });
}

async function countOneToTen(){// 10초동안 1초마다 메시지 출력
    for (let x of [...Array(10).keys()]){//0~9까지 루프 순회
        let result = await waitOneSecond(`${x+1}초 대기중`);//1초 대기후 result에 결과값 저장
        console.log(result);
    }
    console.log("완료");
}

countOneToTen();