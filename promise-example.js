const axios = require ("axios");//axios 임포트
// 영화 순위 정보
const url = "https://raw.githubusercontent.com/wapj/musthavenodejs/main/movieinfo.json";

axios.get(url).then((result) => { // get요청 후 결과값 처리
    if (result.status != 200){
        throw new Error("요청실패"); // 상태 200 아니면 에러
    }

    if (result.data){ // 데이터가 있으면 결과 반환
        return result.data;
    }

    throw new Error("데이터 없슴.");
}).then((data) => {//위에서 받은 데이터 처리
    if (!data.articleList || data.articleList.size == 0 ){//크기가 0이면 에러
        throw new Error ("데이터가 없습니다.");
    }
    return data.articleList; // 영화 리스트 반환
}).then((article) =>{
    return article.map((article,idx) => { // 영화 리스트를 제목과 순위 정보로 분리
        return {title : article.title, rank:idx+1};
    });
}).then((result) => { 
    for (let movieInfo of result) { // 받은 영화 리스트 정보 출력
        console.log(`[${movieInfo.rank}위] ${movieInfo.title}`);
    }
}).catch((err)=>{ // 중간에 발생한 에러들을 여기서 처리
    console.log("<<에러발생>>");
    console.log(err);
});
