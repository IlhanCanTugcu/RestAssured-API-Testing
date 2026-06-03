# RestAssured API Test Otomasyonu

Bu proje, [RestAssured](https://rest-assured.io/) ve **JUnit 5** kullanılarak [JSONPlaceholder](https://jsonplaceholder.typicode.com/) sahte (mock) API'si üzerinde temel HTTP isteklerinin (GET, POST) nasıl test edileceğini gösteren bir API test otomasyonu örneğidir.

## 📂 Test Senaryoları

Proje içerisindeki `JsonPlaceholderTest` sınıfı temel olarak şu senaryoları test eder:

1. **Tek Bir Gönderi Getirme (GET /posts/1):**
   * İlgili endpoint'e istek atılır.
   * Durum kodunun `200 OK` olduğu, yanıt süresinin 3 saniyenin altında olduğu ve dönen JSON verisindeki `id`, `userId` gibi alanların doğruluğu kontrol edilir.

2. **Tüm Gönderileri Getirme (GET /posts):**
   * Tüm gönderilerin listelendiği endpoint'e istek atılır.
   * Durum kodunun `200 OK` olduğu ve dönen listede tam olarak 100 adet gönderi bulunduğu doğrulanır.

3. **Yeni Gönderi Oluşturma (POST /posts):**
   * Endpoint'e JSON formatında yeni bir gönderi verisi yollanır (`title`, `body`, `userId`).
   * Durum kodunun `201 Created` olduğu, veritabanı simülasyonu tarafından yeni bir `id` atandığı ve gönderilen verilerin doğru bir şekilde yansıdığı kontrol edilir.
