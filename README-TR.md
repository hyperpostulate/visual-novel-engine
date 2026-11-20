<p align="center">
  <img src="src/main/resources/icon.png" alt="visual-novel-engine" width="240" height="240">
</p>

<h1 align="center">visual-novel-engine</h1>

<p align="center">
  <a href="https://github.com/hyperpostulate/visual-novel-engine/blob/main/pom.xml"><img src="https://img.shields.io/badge/Java-25-orange.svg" alt="Java 25"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-GPLv3-blue.svg" alt="License: GPL v3"></a>
  <a href="https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml"><img src="https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml/badge.svg" alt="Java CI"></a>
  <a href="https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml"><img src="https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml/badge.svg" alt="CodeQL"></a>
</p>

<p align="center">Spring Boot ve Java Swing API ile oluşturulmuş bir görsel roman motoru.</p>
<p align="center">Hikayeler, sahnelerin başlık, metin, resim ve dallanma butonları içerdiği XML formatında tanımlanır.</p>

<p align="center">English version: <a href="README.md">README.md</a></p>

---

## İçindekiler

- [Gereksinimler](#gereksinimler)
- [Kurulum](#kurulum)
- [Derleme ve Test](#derleme-ve-test)
- [CI/CD](#cicd)
- [Mimari](#mimari)
- [Temel Bileşenler](#temel-bileşenler)
- [Bağımlılıklar](#bağımlılıklar)
- [Kullanım Örnekleri](#kullanım-örnekleri)
- [Yapılandırma](#yapılandırma)
- [Testler](#testler)
- [Lisans](#lisans)
- [Geliştirici](#geliştirici)
- [Katkıda Bulunma](#katkıda-bulunma)

---

## Gereksinimler

| Gereksinim | Sürüm |
|------------|-------|
| Java       | 25+   |
| Maven      | 3.8+  |

---

## Kurulum

### Maven

```xml
<dependency>
    <groupId>org.mesutormanli</groupId>
    <artifactId>visual-novel-engine</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Manuel Derleme

```bash
git clone https://github.com/hyperpostulate/visual-novel-engine.git
cd visual-novel-engine
mvn clean install
```

---

## Derleme ve Test

```bash
./mvnw package                          # Tam derleme + testler + paketleme
./mvnw spring-boot:run                  # Uygulamayı çalıştır
java -jar target/visual-novel-engine-0.0.1-SNAPSHOT.jar  # Paketlenmiş JAR ile çalıştır
./mvnw test                             # Sadece testleri çalıştır
```

---

## CI/CD

GitHub Actions iş akışları (`.github/workflows/`):

### maven.yml
- **Tetikleme**: Her push'ta çalışır
- **Ortam**: Ubuntu-latest
- **JDK**: Amazon Corretto 25
- **Komut**: `mvn -B package --file pom.xml`

### codeql.yml
- **Tetikleme**: `master` dalına push/PR ve haftalık zamanlama
- **Dil**: Java
- **Amaç**: Güvenlik açığı taraması

---

## Mimari

```
visual-novel-engine/
├── .github/
│   └── workflows/
│       ├── maven.yml                  # CI derleme iş akışı
│       └── codeql.yml                 # Güvenlik tarama iş akışı
├── src/
│   ├── main/
│   │   ├── java/org/mesutormanli/visualnovel/engine/
│   │   │   ├── VisualNovelEngineApplication.java   # Spring Boot giriş noktası
│   │   │   ├── Director.java                        # Orkestratör
│   │   │   ├── MainFrame.java                       # Singleton JFrame
│   │   │   ├── Scene.java                           # Sahneler için JPanel
│   │   │   ├── ScenePanelBuilder.java               # Metin/resim/buton panellerini oluşturur
│   │   │   ├── SceneNavigationHandler.java          # Gezinme arayüzü
│   │   │   ├── SceneButtonActionListener.java       # Buton tıklama işleyicisi
│   │   │   ├── SceneStateLogger.java                # Saha durumu loglayıcı
│   │   │   ├── ThemeInitializer.java                # FlatDarkLaf tema kurulumu
│   │   │   ├── config/
│   │   │   │   ├── MainConfig.java                  # Uygulama yapılandırması (properties okur)
│   │   │   │   ├── SceneLayoutFactory.java           # Yerleşim fabrikası
│   │   │   │   ├── StoryConfigFactory.java           # JAXB ile XML ayrıştırıcı
│   │   │   │   └── story/
│   │   │   │       ├── StoryConfig.java             # Kök XML elemanı
│   │   │   │       ├── SceneConfig.java             # Sahn XML elemanı
│   │   │   │       └── ButtonConfig.java            # Buton XML elemanı
│   │   │   └── util/
│   │   │       ├── RelativeLayout.java              # Özel orantılı yerleşim yöneticisi
│   │   │       └── StringUtils.java                 # HTML sarma yardımcıları
│   │   └── resources/
│   │       ├── application.properties               # Çalışma zamanı yapılandırması
│   │       ├── icon.png                             # Uygulama simgesi
│   │       ├── story.xml                            # Örnek hikaye (5 sahne)
│   │       └── images/                              # Sahn başlık GIF'leri
│   └── test/
│       └── java/.../VisualNovelEngineApplicationTests.java
├── lib/
│   └── jaco-mp3-player-0.9.3.jar      # Dahili MP3 çalar (planlanan)
├── pom.xml                            # Maven proje tanımlayıcısı
├── mvnw                               # Maven wrapper (Unix)
└── mvnw.cmd                           # Maven wrapper (Windows)
```

### Akış

```
main() → SpringApplicationBuilder(headless=false)
      → Director.action() [Swing EDT]
      → ThemeInitializer.initialize() [FlatDarkLaf]
      → MainFrame.initialize() [singleton JFrame]
          → StoryConfigFactory [story.xml'i Map<index, SceneConfig>'ye dönüştürür]
          → Scene(0, ...) [başlangıç sahnesi]
              → ScenePanelBuilder [metin/resim/buton panelleri]
              → SceneButtonActionListener ile butonlar
                  → MainFrame.navigateToScene(nextSceneConfig)
                      → new Scene(nextScene, ...)
```

---

## Temel Bileşenler

| Bileşen | Dosya | Açıklama |
|---------|-------|----------|
| `VisualNovelEngineApplication` | `VisualNovelEngineApplication.java` | Swing için `headless(false)` ile Spring Boot giriş noktası |
| `Director` | `Director.java` | Temayı ve MainFrame'i başlatan orkestratör |
| `MainFrame` | `MainFrame.java` | Saha yaşam döngüsünü yöneten singleton JFrame |
| `Scene` | `Scene.java` | Tek bir sahneyi temsil eden JPanel (metin, resim, butonlar) |
| `ScenePanelBuilder` | `ScenePanelBuilder.java` | Sahneler için üç alt paneli oluşturur |
| `StoryConfigFactory` | `StoryConfigFactory.java` | `story.xml`'i JAXB ile `Map<Integer, SceneConfig>`'ye dönüştürür |
| `MainConfig` | `MainConfig.java` | `@Value` enjeksiyonu ile `application.properties`'i okur |
| `ThemeInitializer` | `ThemeInitializer.java` | FlatLaf Dark temasını uygular |
| `RelativeLayout` | `RelativeLayout.java` | Özel orantılı yerleşim yöneticisi |

---

## Bağımlılıklar

| Bağımlılık | Sürüm | Amaç |
|------------|-------|------|
| Spring Boot Starter | 4.1.0 | Çekirdek çerçeve (otomatik yapılandırma, loglama) |
| Spring Boot Starter Test | 4.1.0 | JUnit 5, Mockito, Spring Test |
| FlatLaf | 3.7.1 | Modern koyu Swing görünüm teması |
| Jakarta XML Bind API | (yönetilen) | XML ayrıştırma için JAXB API |
| JAXB Runtime | 4.0.8 | JAXB uygulaması (GlassFish) |

---

## Kullanım Örnekleri

### Motoru Çalıştırma

```bash
# Maven ile doğrudan çalıştırma
./mvnw spring-boot:run

# Veya paketlenmiş JAR ile çalıştır
java -jar target/visual-novel-engine-0.0.1-SNAPSHOT.jar
```

### XML'de Hikaye Tanımlama

```xml
<?xml version="1.0" encoding="UTF-8"?>
<story>
    <scene>
        <index>0</index>
        <headLine>Sahne 0 Başlığı</headLine>
        <text>Hoş geldiniz! Yolunuzu seçin:</text>
        <button>
            <description>Sahne 1'e Git</description>
            <targetSceneIndex>1</targetSceneIndex>
        </button>
        <button>
            <description>Sahne 2'ye Git</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>

    <scene>
        <index>1</index>
        <headLine>Sahne 1 Başlığı</headLine>
        <text>Sahne 1'desiniz. Yol ileriye doğru devam ediyor.</text>
        <button>
            <description>Sahne 2'ye Git</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>

    <scene>
        <index>2</index>
        <headLine>Sahne 2 Başlığı</headLine>
        <text>Son! Oynadığınız için teşekkürler.</text>
        <button>
            <description>Yeniden Başla</description>
            <targetSceneIndex>0</targetSceneIndex>
        </button>
    </scene>
</story>
```

### Programatik Gezinme

```java
// MainFrame, SceneNavigationHandler'ı uygular
MainFrame mainFrame = MainFrame.getInstance();

// Bir sahneye gezin
SceneConfig nextScene = storyConfigFactory.getSceneConfigMap().get(1);
mainFrame.navigateToScene(nextScene);
```

---

## Yapılandırma

Tüm çalışma zamanı yapılandırması `src/main/resources/application.properties` dosyasında tanımlıdır.

| Özellik | Varsayılan | Açıklama |
|---------|-----------|----------|
| `app.title` | `Visual Novel Engine` | Pencere başlığı |
| `app.width` | `800` | Pencere genişliği (piksel) |
| `app.height` | `600` | Pencere yüksekliği (piksel) |
| `app.icon-path` | `/icon.png` | Uygulama simgesi yolu (classpath) |
| `app.story-config-path` | `story.xml` | Hikaye XML dosyası yolu (classpath) |
| `app.scene-images-dir` | `/images/` | Sahn başlık resimleri dizini (classpath) |
| `app.image-file-postfix` | `.gif` | Resim dosyası uzantısı |

---

## Testler

Testler `src/test/java/` konumunda bulunur ve uygulama bağlamının hatasız başladığını doğrulamak için **Spring Boot Test** kullanır.

### Testleri Çalıştırma

```bash
./mvnw test                         # Tüm testleri çalıştır
```

### Test Yardımcıları

Tek test sınıfı `VisualNovelEngineApplicationTests` şunları doğrular:

- Spring Boot uygulama bağlamı hatasız başlar

---

## Lisans

Bu proje GNU General Public License v3.0 altında lisanslanmıştır. Detaylı bilgi için [LICENSE](LICENSE) dosyasına bakın.

---

## Geliştirici

**Mesut ORMANLI**

- E-posta: [mesutormanli@gmail.com](mailto:mesutormanli@gmail.com)
- GitHub: [@hyperpostulate](https://github.com/hyperpostulate)

---

## Katkıda Bulunma

1. Depoyu fork edin
2. Bir özellik dalı oluşturun (`git checkout -b feature/new-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add new feature'`)
4. Dalı itin (`git push origin feature/new-feature`)
5. Bir Pull Request oluşturun
