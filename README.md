<h1 align="center"> Progetto-CurrencyLayer</h1></font>
<p align="center">
    <img src=https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/readme/Images/currencylayer_logo.png?raw=true>
</p>
	

> La nostra applicazione permette di fare  confronti tra valute scelte  dall'utente nell'arco temporale di 7 giorni e riportare statistiche dinamiche quali media, varianza, minimi e massimi delle stesse in base ad un range di giorni settimanale definito dall'utente. L'applicazione è stata sviluppata come progetto d'esame per il corso di Programmazione ad oggetti, per l'Anno Accademico 2021/22, presso l'Università Politecnica delle Marche.
  *** 
  
  ## *Indice*

  -  [Introduzione](#introduzione)
  -  [Sviluppo](#Sviluppo)
  - [Download](#download)
  -  [Configurazione](#configurazione)

  - [Rotte](#rotte)

  - [Eccezzioni](#Eccezioni )

  - [Autori](#autori)

<a name="introduzione"></a>
## *Introduzione*
  <a>Lo scopo del progetto è quello di creare una applicazione in grado di interagire con l' API di [Currencylayer](https://currencylayer.com/).
  Si concentra principalmente sul fornire statistiche riguardo monete fisiche ed elettroniche in un range di giorni definito dall'utente. 
  Prevede inoltre alcune rotte aggiuntive dedicate alla conversione da  una o più valute ad altre valute  scelte dall'utente.</a>
  
  <a name="download"></a>
  
  <a name="Sviluppo"></a>
  ## *Sviluppo*
  <a>L'applicazione è stata sviluppata tramite il framework Springboot, e le dipendenze sono state gestite con Maven. 
Le varie classi Java all'interno del progetto, sono state modellate seguendo i principi della programmazione ad oggetti. Lavorando con coppie di valute, si è pensato di creare la classe  `Currency` per rappresentare la singola valuta, ed estendere tale classe con la classe  `Pair` , formata da una coppia di valute, e avente inoltre un Exchange rate.
	
Le interfacce implementate,  `StatsInterface` e `Converter`, astraggono alcuni concetti come la conversione e il calcolo delle statistiche. Non sarà quindi necessario, in futuro, andare a creare nuovi metodi per effetture conversioni e statistiche su altri oggetti modellati in Java (Come ad esempio Azioni, Materie prime ecc).
	
La classe `DataParser` gestisce l'interazione con l'API di CurrencyLayer, grazie all'utilizzo della libreria org.json.

Al termine della realizzazione del progetto, sono stati effettuati test unitari con JUnit 5.
</a>
 ## *Download*
  <a>Puoi scaricare il codice dal Prompt dei Comandi digitando:  
   ```
   git clone https://github.com/Luca-Pigliacampo/Esame_2022-01.git
   ```
  </a>

 ## *Installazione*(Linux) 
  <a>Per ottenere l'eseguibile in formato jar su un sistema basato su GNU/Linux bisogna poi:
   ```
   cd Esame_2022-01/Currency_Layer # entrare nella directory del progetto
   ./mvnw package                  # compilare e creare il file eseguibile
   ```
   a questo punto il file jar dovrebbe essere stato creato nella cartella `target` e potrà essere eseguito con:
   ```
   java -jar <file>
   ```
  </a>

 ## *Installazione*(Windows)
  <a>Per avere l'eseguibile in formato jar su Windows invece:
   ```
   cd Esame_2022-01\Currency_Layer # entrare nella directory del progetto
   .\mvnw.cmd                      # compilare e creare l'eseguibile
   ```
   ora il file jar si trova nella cartella `target` e può essere eseguito con:
   ```
   java -jar <file>
   ```
  </a>

 ## *Installazione*(Mac)
  <a>Dovrebbero andare bene le istruzioni per linux, ma non è stato verificato</a>

  <a name="configurazione"></a>
  ## *Configurazione*
  Per accedere al nostro servizio è necessario modificare la variabile ```api_key``` in [JSONParser.java](https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/main/Currency_Layer/src/main/java/com/currencylayer/parse/JSONParser.java).
Si può ottenere una API key gratuitamente accedendo alla pagina di [Currencylayer](https://currencylayer.com/).
Infine basterà avviare il web-server eseguendo [CurrencyLayerApplication.java](https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/main/Currency_Layer/src/main/java/com/currencylayer/CurrencyLayerApplication.java).
  
  <a name="rotte"></a>
  ## *Rotte*
  Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo
```
localhost:8080
```
Le rotte definite sono le seguenti:

N° | Tipo | Rotta | Descrizione
----- | ------------ | -------------------- | ----------------------
[1](#1) | ` GET ` | `/conversion?src=EUR&tgt=ZWL&amount=1` | *restituisce il tasso di cambio tra le varie valute in ```src``` e ```tgt``` nonche la coversione in base all' ```amount``` scelto.*
[2](#2) | ` GET ` | `/live?src=EUR&tgt=GBP` | *restituisce il tasso di conversione tra le valute scelte.*
[3](#3) | ` GET ` | `/stat?cur=EUR&bas=GBP&opt=average,minimum,variance,maximium&std=2021-12-22&end=2021-12-24` | *restituisce le statistiche in base al range in giorni definito dall'utente sull'andamento del valore di una valuta come media e varianza delle stesse e numero minimo/massimo/medio di valore. Il range in giorni deve essere compreso tra il ```2021-12-24``` e il ```2021-12-18```.*
  
  - ## Come può l'utente effettuare richieste? Cosa riceverà in risposta? 

Basta avviare il programma come applicazione SpringBoot, assicurarsi di avere Postman e seguire le seguenti istruzioni.

Ora illustreremo alcuni esempi su cosa dare in richiesta e cosa dovete aspettarvi in risposta.

<a name="1"></a>
## 1.   /conversion?src= &tgt= &amount=

La prima rotta prevede l'inserimento di tre parametri :
 - ***src*** indica le valute di partenza (EUR,GBP,USD...);
 - ***tgt*** indica le valute diritorno;
 - ***amount*** indica la quantità da convertire che può essere anche decimale ma non negativa.
 
Questa rotta restituisce un JSONArray di questo tipo contenente sia le informazioni sulle singole valute sia il risultato della conversione.
Potete inserire anche più di una  valuta in ***src*** e ***tgt***, a condizione che esse esistano.

![alt_text](https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/readme/Images/rotta_conversion.png?raw=true)

<a name=2></a>
## 2.   /live?src= &tgt= 

La seconda rotta vuole invece come parametro solo due valute e restituisce il tasso di cambio attuale tra le due.

![alt_text](https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/readme/Images/rotta_live.png?raw=true)

<a name=3></a>
## 3.   /stat?cur= &bas= &opt= &std= &end= 
La terza rotta è la rotta delle statistiche e prevede i seguenti parametri:
 - ***cur*** che indica le valute  di cui vogliamo le statistiche
 - ***bas*** la valuta da usare come unità di misura
 - ***opt*** indica quale informazione si vuole tra le disponibili 
 - ***std*** la data di inizio delle misurazioni
 -  ***end*** la data di fine delle misurazioni
 
 Il risultato è un JSONObject contenente le informazioni sulla valuta scelta e sulla statistica scelta in ***opt***.
 Per qualsiasi dubbio è disponibile la documentazione all'url <https://github.com/Luca-Pigliacampo/Esame_2022-01/tree/main/Currency_Layer/doc>,poichè è in html la si deve prima  scaricare e poi aprire index.html con un qualsiasi motore di ricerca.
 
 ![alt_text](https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/readme/Images/rotta_stats.png?raw=true)
<a name="Eccezzioni"></a>
## *Eccezzioni*
Il programma può lanciare anche eccezioni personalizzate
> Eccezioni personalizzate:
- **AmountFormatException.java**
  
    Viene richiamata quando il programma riconosce che l'amount è negativo
  Viene visualizzato il seguente messaggio di errore:
```
    {
    "message": "Inserisci un valore positivo",
    "httpStat": "BAD_REQUEST",
    "time": "2022-01-08T12:09:25.2028348+01:00"
     }
```
- **CurrencyNotFoundException.java**
  
   
 Viene richiamata quando il programma ottiene in input una valita inesistente.
Viene visualizzato il seguente messaggio di errore:
```
{
    "message": "La valuta CAI non esiste",
    "httpStat": "BAD_REQUEST",
    "time": "2022-01-08T12:07:18.8891748+01:00"
}
```
- **DateErrorException.java**
 
 Viene richiamata quando il programma ottiene una data in un formato errato.
 Viene visualizzato il seguente messaggio di errore:
 
```
{
    "message": "Errore nel formato della data. Il formato corretto è YYYY-MM-DD.",
    "httpStat": "BAD_REQUEST",
    "time": "2022-01-17T12:36:55.5340542+01:00"
}
```
- **SameCurrencyException.java**

Viene richiamata quando si inseriscono le stesse valute (ES: EUR-EUR).

Viene visualizzato il seguente messaggio di errore:
```
{
    "message": "Non puoi inserire due valute uguali!",
    "httpStat": "BAD_REQUEST",
    "time": "2022-01-12T14:21:11.1275878+01:00"
}

```



	


<a name="autori"></a>
  ## *Autori*
  Nome |  Contributo
  ---- |  -----------
  [Maio Mario](https://www.linkedin.com/in/mario-maio/) | 33%
  Partemi Emanuele | 33%
  Pigliacampo Luca | 33%
 
  

  

