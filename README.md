<h1 align="center"> Progetto-CurrencyLayer</h1></font>
<p align="center">
    <img src=https://github.com/Luca-Pigliacampo/Esame_2022-01/blob/readme/Images/currencylayer_logo.png?raw=true>
</p>
	

> La nostra applicazione permette di fare  confronti tra valute scelte  dall'utente nell'arco temporale di 7 giorni e riportare statistiche dinamiche quali media e varianza delle stesse in base ad un range di giorni settimanale definito dall'utente.
  *** 
  
  ## *Indice*

  -  [Introduzione](#introduzione)
  - [Download](#download)
  -  [Configurazione](#configurazione)

  - [Rotte](#rotte)

  - [Eccezzioni](#Eccezioni )

  - [Autori](#autori)

<a name="introduzione"></a>
## *Introduzione*
  <a>Lo scopo del progetto è quello di creare un programma Currency_Layer in grado di interagire con l' API di [Currencylayer](https://currencylayer.com/).
  Si concentra principalmente sul fornire statistiche riguardo alcune monete in un range di giorni definito dall'utente. 
  Prevede inoltre alcune rotte aggiuntive dedicate alla conversione da    una o più valute ad altre valute  scelte dall'utente.</a>
  
  <a name="download"></a>
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

```


	


<a name="autori"></a>
  ## *Autori*
  Nome |  Contributo
  ---- |  -----------
  Maio Mario | 33%
  Partemi Emanuele | 33%
  Pigliacampo Luca | 33%
 
  

  

