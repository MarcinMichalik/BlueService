# Investment calculator
Aplikacja wykonująca automatyczny podział podanej kwoty inwestycji dla wybranego stylu inwestowania oraz fundszy.

#### Etap 1
Rozwiązanie problemu z etapu 1 znajduję się w klasie o nazwie: ```InvestmentCalculatorImpl```

#### Etap 2
W klasie ```InvestmentWithInseparableAmountCalculatorImpl``` jest implementacja podziału z kwotą nierozdzielną 
opisanego w etapie 2.

### Wymagania
Do poprawnego działania aplikacji wymagana jest Java w minimum wersji 8 (Java 8+).

### Narzędzie do budowania projektu
W projekcie użyto Gradle'a wraz z Gradle wrapperem. Nie wymagana jest instalacja Gradle'a. W celu uruchomienia np. 
testów wystarczy wykonać polecenie:

```bash
./gradlew test
```

Do projektu również zostało dodane narzędzie do sprawdzania pokrycia kodu testami. W celu wygenerowania raportu należy
użyć polecenia:

```bash
./gradlew jacocoTestReport
```

W folderze ```build/jacocoHtml``` znajduję się plik ```index.html```, który przedstawia raport pokrycia kodu testami.