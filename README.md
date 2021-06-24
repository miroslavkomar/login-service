# Login Service

**Spustenie:**
Je potrebné stiahnuť projektové súbory a spustiť triedu main (src/komarm/main/main.java)

Vzhľadom k pokročilejším implementačným problémom riešenie neobsahuje:
!Riešenie neobsahuje automatickú aktualizáciu na DB!
!Riešenie neobsahuje Docker! 
!Riešenie neobsahuje pokrytie corner cases!

Vzhľadom na to, ako bolo zadanie pochopené bola vytvorená implementácia nasledujúcim spôsobom:
  - spustenie aplikácie predstavuje simuláciu fungujucého systému za jeden deň
  - po spustení implementácie je možné ju testovať pomocou interakcie s konzolou

**Po spustení:**
  1. objaví sa výzva aby sme zadali rate prihlasení
  2. náhodne sa vygenerujú prihlásenia (Simuluje LoginService)
  3. logy z prihlásení sa odošlú ďalej pre spracovanie dát podľa DB (Simuluje LoginLogsAggService)
  4. zobrazia sa nezoradené záznamy prihláseni z jednotlivých krajín 
  5. objaví sa výzva aby sme zadali koľko záznamov chcem zobraziť (Simuluje request Statistics)
  6. zobrazí sa výstup pre "aplikáciu" Statistics
  
**LoginService trieda:**
Vygeneruje prihlásenia, ktoré sú odoslané ďalej pre spracovanie triede LoginLogsAggService

**#LoginLogsAggService trieda:**
Podľa vstupných logov trieda z DB vyberie pre ktorú krajinu daná IP patrí a zapíše záznam

**#Statistics trieda:**
Simuluje aplikáciu requestujúcu spracované dáta z LoginLogsAggService triedy

**main trieda:**
Slúži na spúšťanie simulácie

