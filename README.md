# Quartz & Spring Batch Sample

> h2DB 사용 - 별도 DataBase 설정 없이 실행 가능

### JAVA Version
- OpenJDK 1.8

### 실행 방법
- 별도 Profile 설정 없이 서버 시작
- http://localhost:8080/h2-console
  - JDBC URL : jdbc:h2:mem:testdb
  - User Name : sa
  - Password : 

### endpoint
- `/batch-client/start-credit-job` 
- `/batch-client/start-user-job` 

### 적용한 기능
- JdbcCursorItemReader > JdbcBatchItemWriter  
  - `junseok.snr.batchscheduler.batch.dtabase.JdbcBatchConfiguration`
- FlatFileItemReader > JdbcBatchItemWriter  
  - `junseok.snr.batchscheduler.batch.sample.BatchConfiguration`

### Spring Batch Controller Test
- RestTemplate으로 통합 테스트
