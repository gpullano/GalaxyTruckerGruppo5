# Galaxy Trucker - Gruppo 5

## Il Gioco
Galaxy Trucker è un gioco da tavolo ideato da Vlaada Chvátil in cui i giocatori assumono il ruolo di camionisti spaziali. Il gioco si divide in due fasi principali:
1. **Costruzione**: I giocatori assemblano freneticamente le loro navi spaziali usando tessere componente, cercando di bilanciare velocità, potenza di fuoco, capacità di carico e difese.
2. **Volo**: Le navi appena costruite affrontano un pericoloso viaggio attraverso una serie di carte avventura, che possono includere piogge di meteoriti, pirati spaziali, pianeti da esplorare e molto altro.
L'**obiettivo** è sopravvivere al viaggio con la nave (e l'equipaggio) più intatta possibile e arrivare a destinazione con il carico più redditizio per guadagnare crediti cosmici. Spesso, il divertimento sta proprio nel vedere la propria nave, costruita con tanta fatica, andare gloriosamente in pezzi.
_Questo progetto è un adattamento testuale a turni che mira a catturare lo spirito e le meccaniche fondamentali del gioco originale._

**Come giocare: entra nel pacchetto "galaxytrucker" ed esegui il main.**

### Compromessi e scelte nel gameplay
*Considerazioni generali*
- Abbiamo implementato solo il livello 1 (non il volo di prova),
- non abbiamo implementato la clessidra,
- non abbiamo implementato le stive speciali,
- ogni stiva normale ha 3 spazi merci e può contenere tutti i tipi di merci,
- ogni vanoBatteria ha 3 batterie,
- le tessere e le carte sono generate casualmente,
- le merci/l'equipaggio vengono rimosse scorrendo la nave dalla prima stiva/cabina trovata. La cabina centrale è l'ultima a cui viene tolto l'equipaggio, prima vengono tolti gli astronauti e poi gli alieni,
- quando vengono risolte le carte o si guardano i mazzetti, l'interfaccia stampa un numero di elementi tale che bisogna tornare su con il cursore per vedere gli effetti della propria azione.

*Assemblaggio*

**Tipologie di connettori:**

| Nome del connettore | Simbolo associato |
|---------------------|-------------------|
| Universale          | `U`               |
| Doppio              | `D`               |
| Singolo             | `S`               |
| Cannone             | `+`               |
| Motore              | `M`               |
| CannoneDoppio       | `+2`              |
| MotoreDoppio        | `M2`              |
| Scudo               | `()`              |
| Nullo               | `--`              |

- dopo che si sceglie di ruotare una tessera (n volte), bisogna agganciarla,
- durante l'assemblaggio si possono solo posizionare tessere accanto a quelle gia' posizionate (non diagonalmente). Non si possono creare "isole" di tessere,


*Preparazione al decollo*
- la verifica della corretta connessione dei componenti (cannoni che non puntano verso altre tessere, motori che non puntano indietro, ecc.) viene effettuata a fine dell'assemblaggio, nella fase di preparazione al decollo, insieme al controllo delle tessere non collegate correttamente,

*Volo*
- in carte come "Zona Di Guerra" non viene gestita la parità per i criteri di applicazione delle penalità. Esempio: Il primo che viene trovato con la potenza di fuoco minima, anche se in pareggio con qualche altro giocatore subirà la penalità.


## BUON VOLO!
