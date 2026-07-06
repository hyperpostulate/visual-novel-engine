# visual-novel-engine

[![Java CI](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml/badge.svg)](https://github.com/hyperpostulate/visual-novel-engine/actions/workflows/maven.yml) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

A (yet another) visual novel engine with Spring Boot and Java Swing API. Hikayeleri XML ile tanımlayın, sahne sahne ilerleyerek interaktif romanlar oluşturun.

---

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Build & Test](#build--test)
- [CI/CD](#cicd)
- [Architecture](#architecture)
- [Core Components](#core-components)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Story Format](#story-format)
- [Usage Examples](#usage-examples)
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
mvn clean test                    # Full build + tests
mvn clean package                 # Build + tests + package
mvn spring-boot:run               # Run the application
mvn test -Dtest=VisualNovelEngineApplicationTests  # Single test class
```

---

## CI/CD

GitHub Actions workflow (`.github/workflows/maven.yml`):

- **Trigger**: Runs on every push
- **Environment**: Ubuntu-latest
- **JDK**: Amazon Corretto 25
- **Command**: `mvn -B package`

---

## Architecture

```
org.mesutormanli.visualnovel.engine
├── config/
│   ├── MainConfig.java                # Uygulama yapılandırma (başlık, boyut, ikon)
│   ├── SceneLayoutFactory.java        # Sahne düzeni fabrikası
│   ├── StoryConfigFactory.java        # XML hikaye yapılandırma yükleyici
│   └── story/
│       ├── StoryConfig.java           # Kök XML elemanı (scene listesi)
│       ├── SceneConfig.java           # Sahne yapılandırması
│       └── ButtonConfig.java          # Düğme yapılandırması
├── util/
│   ├── RelativeLayout.java           # Özel layout manager (oranlı yerleşim)
│   └── StringUtils.java              # HTML sarma ve boş string yardimcilari
├── Director.java                     # Ana orkestratör - uygulamayı baslatir
├── MainFrame.java                    # Ana pencere (JFrame singleton)
├── Scene.java                        # Sahne paneli (JPanel)
├── ScenePanelBuilder.java            # Metin, resim ve dugme panellerini olusturur
├── SceneButtonActionListener.java    # Dugme tiklama olaylarini isler
├── SceneNavigationHandler.java       # Sahne navigasyon arayuzu
├── SceneStateLogger.java             # Sahne durumu loglama
├── ThemeInitializer.java             # FlatDarkLaf temasi baslatma
└── VisualNovelEngineApplication.java # Spring Boot ana sinif
```

### Core Components

| Bileşen | Açıklama |
|---------|----------|
| `Director` | Uygulama yaşam döngüsünü yönetir. Spring Boot başladıktan sonra `MainFrame`'i başlatır |
| `MainFrame` | Ana pencere (singleton JFrame). Sahne navigasyonunu yönetir |
| `Scene` | Her sahneyi temsil eden JPanel. Metin, resim ve düğmeleri içerir |
| `ScenePanelBuilder` | Scene içindeki metin, resim ve düğme panellerini inşa eder |
| `SceneButtonActionListener` | Düğme tıklamalarını yakalar ve bir sonraki sahneye geçişi tetikler |
| `SceneNavigationHandler` | Sahne geçişleri için arayüz (`navigateToScene`, `handleNavigationError`) |
| `SceneStateLogger` | Mevcut sahne ve olası durumları loglar |
| `ThemeInitializer` | FlatDarkLaf (koyu tema) başlatır |
| `MainConfig` | `application.properties` değerlerini yükler |
| `StoryConfigFactory` | `story.xml` dosyasını JAXB ile ayrıştırır |
| `SceneLayoutFactory` | RelativeLayout ve FlowLayout düzenleri oluşturur |
| `RelativeLayout` | Oranlı yerleşim sağlayan özel layout manager |

### Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Spring Boot Starter | 4.1.0 | Uygulama çerçevesi |
| FlatLaf | 3.7.1 | Modern koyu tema (Look and Feel) |
| Jakarta XML Bind API | - | JAXB ile XML çözümleme |
| JAXB Runtime | 4.0.8 | JAXB çalışma zamanı desteği |
| SLF4J | - | Loglama arayüzü |
| JUnit Jupiter | - | Test framework |

---

## Configuration

Tüm yapılandırma `src/main/resources/application.properties` dosyasında tanımlıdır:

| Property | Default | Açıklama |
|----------|---------|----------|
| `app.title` | `Visual Novel Engine` | Pencere başlığı |
| `app.width` | `800` | Pencere genişliği (px) |
| `app.height` | `600` | Pencere yüksekliği (px) |
| `app.icon-path` | `/icon.png` | Uygulama ikonu yolu |
| `app.story-config-path` | `story.xml` | Hikaye XML dosyası yolu |
| `app.scene-images-dir` | `/images/` | Sahne resimlerinin bulunduğu dizin |
| `app.image-file-postfix` | `.gif` | Resim dosya uzantısı |

---

## Story Format

Hikayeler `story.xml` dosyasında XML formatında tanımlanır:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<story>
    <scene>
        <index>0</index>
        <headLine>Baslik</headLine>
        <text>Sahne metni buraya yazilir.</text>
        <button>
            <description>Bir sonraki sahneye git</description>
            <targetSceneIndex>1</targetSceneIndex>
        </button>
        <button>
            <description>Baska bir sahneye git</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>
</story>
```

### XML Elemanları

| Eleman | Açıklama |
|--------|----------|
| `<story>` | Kök eleman. Tüm sahneleri içerir |
| `<scene>` | Bir sahneyi tanımlar |
| `<index>` | Sahnenin benzersiz indeksi (0'dan başlar) |
| `<headLine>` | Sahnede görünen başlık. Aynı zamanda resim dosyası adı olarak kullanılır |
| `<text>` | Sahne metni |
| `<button>` | Bir navigasyon düğmesi tanımlar |
| `<description>` | Düğme üzerinde görünen yazı |
| `<targetSceneIndex>` | Düğmeye tıklandığında gidilecek sahnenin indeksi |

### Resim Dosyaları

Sahne resimleri `src/main/resources/images/` dizininde bulunur. Dosya adı `<headLine>.<image-file-postfix>` formatında olmalıdır. Örneğin, `<headLine>Baslik</headLine>` için `images/Baslik.gif` dosyası beklenir.

---

## Usage Examples

### Basit Bir Hikaye Oluşturma

```xml
<!-- story.xml -->
<story>
    <scene>
        <index>0</index>
        <headLine>Giris</headLine>
        <text>Hikayeye hoş geldiniz! Nereye gitmek istersiniz?</text>
        <button>
            <description>Ormana git</description>
            <targetSceneIndex>1</targetSceneIndex>
        </button>
        <button>
            <description>Sehre git</description>
            <targetSceneIndex>2</targetSceneIndex>
        </button>
    </scene>

    <scene>
        <index>1</index>
        <headLine>Orman</headLine>
        <text>Ormanin icindesiniz. Agaclarin arasindan bir yol gorunuyor.</text>
        <button>
            <description>Yolu takip et</description>
            <targetSceneIndex>3</targetSceneIndex>
        </button>
        <button>
            <description>Geri don</description>
            <targetSceneIndex>0</targetSceneIndex>
        </button>
    </scene>
</story>
```

### Uygulamayi Calistirma

```bash
mvn spring-boot:run
```

### Konfigürasyon Değiştirme

```properties
# application.properties
app.title=Benim Hikayem
app.width=1024
app.height=768
app.story-config-path=my-story.xml
app.scene-images-dir=/my-images/
```

### Uygulama Mantigi

1. `VisualNovelEngineApplication.main()` Spring Boot'u başlatır
2. `Director.action()` EDT (Event Dispatch Thread) üzerinde çalıştırılır
3. `ThemeInitializer` FlatDarkLaf temasını uygular
4. `MainFrame` pencereyi oluşturur ve ilk sahneyi yükler
5. `StoryConfigFactory` `story.xml` dosyasını JAXB ile okur
6. Her sahnede `ScenePanelBuilder` metin, resim ve düğme panellerini oluşturur
7. Düğmeye tıklandığında `SceneButtonActionListener` bir sonraki sahneye geçişi tetikler
8. Hedef sahne bulunamazsa `handleNavigationError` çağrılır ve uygulama kapanır

---

## Testing

Tüm testler Spring Boot Test ile entegre edilmiştir.

### Mevcut Testler

- `VisualNovelEngineApplicationTests` - Spring context yüklenme testi

### Test Çalıştırma

```bash
# Tüm testler
mvn test

# Tek test sınıfı
mvn test -Dtest=VisualNovelEngineApplicationTests
```

---

## License

Bu proje GNU General Public License v3.0 altında lisanslanmıştır. Detaylı bilgi için [LICENSE](LICENSE) dosyasına bakın.

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
