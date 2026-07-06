# visual-novel-engine

[![Java CI](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml/badge.svg)](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml) [![CodeQL](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml/badge.svg)](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml)

A (yet another) visual novel engine with Spring Boot and Java Swing API. Stories are defined in XML with scenes containing headlines, text, images, and branching buttons.

---

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Build & Test](#build--test)
- [CI/CD](#cicd)
- [Architecture](#architecture)
- [Core Components](#core-components)
- [Dependencies](#dependencies)
- [Usage Examples](#usage-examples)
- [Configuration](#configuration)
- [Testing](#testing)
- [License](#license)
- [Developer](#developer)
- [Contributing](#contributing)

---

## Requirements

| Requirement | Version |
|-------------|---------|
| Java        | 25+     |
| Maven       | 3.8+    |

---

## Installation

### Maven

```xml
<dependency>
    <groupId>org.mesutormanli</groupId>
    <artifactId>visual-novel-engine</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Manual Build

```bash
git clone https://github.com/hyperpostulate/visual-novel-engine.git
cd visual-novel-engine
mvn clean install
```

---

## Build & Test

```bash
./mvnw package                          # Full build + tests + package
./mvnw spring-boot:run                  # Run the application
java -jar target/visual-novel-engine-0.0.1-SNAPSHOT.jar  # Run packaged JAR
./mvnw test                             # Run tests only
```

---

## CI/CD

GitHub Actions workflows (`.github/workflows/`):

### maven.yml
- **Trigger**: Runs on every push
- **Environment**: Ubuntu-latest
- **JDK**: Amazon Corretto 25
- **Command**: `mvn -B package --file pom.xml`

### codeql.yml
- **Trigger**: Push/PR to `master`, plus weekly schedule
- **Language**: Java
- **Purpose**: Security vulnerability scanning

---

## Architecture

```
visual-novel-engine/
├── .github/
│   └── workflows/
│       ├── maven.yml                  # CI build workflow
│       └── codeql.yml                 # Security scanning workflow
├── src/
│   ├── main/
│   │   ├── java/org/mesutormanli/visualnovel/engine/
│   │   │   ├── VisualNovelEngineApplication.java   # Spring Boot entry point
│   │   │   ├── Director.java                        # Orchestrator
│   │   │   ├── MainFrame.java                       # Singleton JFrame
│   │   │   ├── Scene.java                           # JPanel for a scene
│   │   │   ├── ScenePanelBuilder.java               # Builds text/image/button panels
│   │   │   ├── SceneNavigationHandler.java          # Navigation interface
│   │   │   ├── SceneButtonActionListener.java       # Button click handler
│   │   │   ├── SceneStateLogger.java                # Scene state logger
│   │   │   ├── ThemeInitializer.java                # FlatDarkLaf theme setup
│   │   │   ├── config/
│   │   │   │   ├── MainConfig.java                  # App config (reads properties)
│   │   │   │   ├── SceneLayoutFactory.java           # Layout factory
│   │   │   │   ├── StoryConfigFactory.java           # XML parser (JAXB)
│   │   │   │   └── story/
│   │   │   │       ├── StoryConfig.java             # Root XML element
│   │   │   │       ├── SceneConfig.java             # Scene XML element
│   │   │   │       └── ButtonConfig.java            # Button XML element
│   │   │   └── util/
│   │   │       ├── RelativeLayout.java              # Custom layout manager
│   │   │       └── StringUtils.java                 # HTML wrapping utilities
│   │   └── resources/
│   │       ├── application.properties               # Runtime config
│   │       ├── icon.png                             # App icon
│   │       ├── story.xml                            # Sample story (5 scenes)
│   │       └── images/                              # Scene headline GIFs
│   └── test/
│       └── java/.../VisualNovelEngineApplicationTests.java
├── lib/
│   └── jaco-mp3-player-0.9.3.jar      # Bundled MP3 player (planned)
├── pom.xml                            # Maven project descriptor
├── mvnw                               # Maven wrapper (Unix)
└── mvnw.cmd                           # Maven wrapper (Windows)
```

### Flow

```
main() → SpringApplicationBuilder(headless=false)
      → Director.action() [Swing EDT]
      → ThemeInitializer.initialize() [FlatDarkLaf]
      → MainFrame.initialize() [singleton JFrame]
          → StoryConfigFactory [parses story.xml → Map<index, SceneConfig>]
          → Scene(0, ...) [initial scene]
              → ScenePanelBuilder [text/image/button panels]
              → Buttons with SceneButtonActionListener
                  → MainFrame.navigateToScene(nextSceneConfig)
                      → new Scene(nextScene, ...)
```

---

## Core Components

| Component | File | Description |
|-----------|------|-------------|
| `VisualNovelEngineApplication` | `VisualNovelEngineApplication.java` | Spring Boot entry point with `headless(false)` for Swing |
| `Director` | `Director.java` | Orchestrator that initializes theme and MainFrame |
| `MainFrame` | `MainFrame.java` | Singleton JFrame managing scene lifecycle |
| `Scene` | `Scene.java` | JPanel representing a single scene (text, image, buttons) |
| `ScenePanelBuilder` | `ScenePanelBuilder.java` | Builds the three sub-panels for a scene |
| `StoryConfigFactory` | `StoryConfigFactory.java` | Parses `story.xml` via JAXB into `Map<Integer, SceneConfig>` |
| `MainConfig` | `MainConfig.java` | Reads `application.properties` via `@Value` injection |
| `ThemeInitializer` | `ThemeInitializer.java` | Applies FlatLaf Dark theme |
| `RelativeLayout` | `RelativeLayout.java` | Custom proportional layout manager |

---

## Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Spring Boot Starter | 4.1.0 | Core framework (auto-config, logging) |
| Spring Boot Starter Test | 4.1.0 | JUnit 5, Mockito, Spring Test |
| FlatLaf | 3.7.1 | Modern dark Swing look & feel |
| Jakarta XML Bind API | (managed) | JAXB API for XML parsing |
| JAXB Runtime | 4.0.8 | JAXB implementation (GlassFish) |

---

## Usage Examples

### Running the Engine

```bash
# Direct run via Maven
./mvnw spring-boot:run

# Or run the packaged JAR
java -jar target/visual-novel-engine-0.0.1-SNAPSHOT.jar
```

### Defining a Story in XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<story>
    <scene>
        <index>0</index>
        <headLine>Headline of Scene 0</headLine>
        <text>Welcome! Choose your path:</text>
        <button>
            <description>Go to Scene 1</description>
            <targetSceneIndex>1</targetSceneIndex>
        </button>
        <button>
            <description>Go to Scene 2</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>

    <scene>
        <index>1</index>
        <headLine>Headline of Scene 1</headLine>
        <text>You are in Scene 1. The path continues forward.</text>
        <button>
            <description>Go to Scene 2</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>

    <scene>
        <index>2</index>
        <headLine>Headline of Scene 2</headLine>
        <text>The End! Thank you for playing.</text>
        <button>
            <description>Restart</description>
            <targetSceneIndex>0</targetSceneIndex>
        </button>
    </scene>
</story>
```

### Programmatic Navigation

```java
// MainFrame implements SceneNavigationHandler
MainFrame mainFrame = MainFrame.getInstance();

// Navigate to a scene
SceneConfig nextScene = storyConfigFactory.getSceneConfigMap().get(1);
mainFrame.navigateToScene(nextScene);
```

---

## Configuration

All runtime configuration is defined in `src/main/resources/application.properties`.

| Property | Default | Description |
|----------|---------|-------------|
| `app.title` | `Visual Novel Engine` | Window title |
| `app.width` | `800` | Window width in pixels |
| `app.height` | `600` | Window height in pixels |
| `app.icon-path` | `/icon.png` | Application icon path (classpath) |
| `app.story-config-path` | `story.xml` | Story XML file path (classpath) |
| `app.scene-images-dir` | `/images/` | Directory for scene headline images (classpath) |
| `app.image-file-postfix` | `.gif` | Image file extension |

---

## Testing

Tests are located in `src/test/java/` and use **Spring Boot Test** to verify the application context loads correctly.

### Running Tests

```bash
./mvnw test                         # Run all tests
```

### Test Helpers

The single test class `VisualNovelEngineApplicationTests` verifies:

- Spring Boot application context starts without errors

---

## License

This project is licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for details.

---

## Developer

**Mesut ORMANLI**

- Email: [mesutormanli@gmail.com](mailto:mesutormanli@gmail.com)
- GitHub: [@hyperpostulate](https://github.com/hyperpostulate)

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

---
---
---

# visual-novel-engine

[![Java CI](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml/badge.svg)](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml) [![CodeQL](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml/badge.svg)](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/codeql.yml)

Spring Boot ve Java Swing API ile oluşturulmuş bir görsel roman motoru. Hikayeler, sahnelerin başlık, metin, resim ve dallanma butonları içerdiği XML formatında tanımlanır.

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
