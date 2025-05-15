<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

  <script type="text/javascript">

  function searchApi(event) {
        // 새로고침 방지 
        event.preventDefault();
        const sidoName = $("#sidoName").val();
        $.ajax({
          url: `/api/air-info?`,
          type: 'GET',
          data: {sidoName},
          success: function (result) {
            const items=result.response.body.items;
            console.log(items);

            let tag="";
            for(let i in items){
              const item = items[i];
              tag += "<tr>"
              + "<td>" + item.stationName + "</td>"
              + "<td>" + item.dataTime + "</td>"
              + "<td>" + item.pm10Value + " ug/m3</td>"
              + "<td>" + item.pm25Value + " ug/m3</td>"
              + "<td>" + item.khaiValue + " ppm</td>"
              + "<td>" + item.so2Value + " ppm</td>"
              + "<td>" + item.coValue + " ppm</td>"
              + "<td>" + item.o3Value + " ppm</td>"
              + "<td>" + item.no2Value + " ppm</td>"
              + "</tr>";
            }
            $("#resultTable tbody").html(tag);
          }
        })
      }

  </script>
</head>

<body>
  <h1>안녕하세요</h1>
  <form onsubmit="searchApi(event)">
    <select name="sidoName" id="sidoName">
      <option value="전국">전국</option>
      <option value="서울">서울</option>
      <option value="부산">부산</option>
      <option value="대구">대구</option>
      <option value="인천">인천</option>
      <option value="광주">광주</option>
      <option value="대전">대전</option>
      <option value="울산">울산</option>
      <option value="경기">경기</option>
      <option value="강원">강원</option>
      <option value="충북">충북</option>
      <option value="충남">충남</option>
      <option value="전북">전북</option>
      <option value="전남">전남</option>
    </select>
    <button>조회</button>
  </form>

  <hr>

  <table id="resultTable" border="1">
    <thead>
      <tr>
        <th>측정 장소</th> 
        <th>측정일시</th> 
        <th>미세먼지(PM10) 농도</th>
        <th>초미세먼지(PM2.5) 농도</th>
        <th>통합대기환경수치</th>
        <th>아황산가스 지수	</th>
        <th>일산화탄소 지수	</th>
        <th>오존 지수	</th>
        <th>이산화질소 지수	</th>
      </tr>
    </thead>
    <tbody>

    </tbody>
  </table>
  
</body>

</html>