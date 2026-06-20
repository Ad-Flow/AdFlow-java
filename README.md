# AdFlow Java Client

Java-библиотека для работы с рекламной сетью AdFlow.

## Установка

Maven:

`pom.xml`
<dependency>
    <groupId>com.adflow</groupId>
    <artifactId>adflow</artifactId>
    <version>1.0.0</version>
</dependency>

Gradle:

implementation 'com.adflow:adflow:1.0.0'

## Использование

AdFlow adflow = new AdFlow("твой_api_ключ");
String ad = adflow.getAdHtml();
System.out.println(ad);

## Методы

- getAd() - JSON
- getAdHtml() - HTML-карточка
- click(id) - клик
- getStats() - статистика
- createAd(title, text, link, icon) - создание

## Ссылки

Сайт: http://144.31.199.202:4800
GitHub: https://github.com/Ad-Flow/AdFlow-java
