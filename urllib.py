import urllib.request
import json

url='http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst'
serviceKey='ServiceKey='+'30EcBK1Uu8ho602yUgCrI%2BuOUZEvmzvnoQR9Yw%2Fuw%2F4ZxxhGIjVI0PF2iNW3wLSdAlboeQGFOGGfXTwqe4iDqQ%3D%3D'
numOfRow='&numOfRows=1000'
pageNo='&pageNo=1'
dataType='&dataType=XML'
base_date='&base_datd='+'20230419'
base_time='&base_time='+'1430'
nx='&nx=68'
ny='&ny=109'

queryParams='?'+serviceKey+numOfRow+pageNo+dataType+base_date+base_time+nx+ny
response=urllib.request.urlopen(url+queryParams).read().decode('utf8')
print(response)

#https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=30EcBK1Uu8ho602yUgCrI%2BuOUZEvmzvnoQR9Yw%2Fuw%2F4ZxxhGIjVI0PF2iNW3wLSdAlboeQGFOGGfXTwqe4iDqQ%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date=20230419&base_time=0630&nx=55&ny=127
