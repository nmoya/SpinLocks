#################################
Nikolas Moya - 144678 - Lista 03
#################################

Basta abrir esta pasta 'SpinLocks' como sendo um projeto do Eclipse.

A execu���o do programa come�a em Begin.java, dentro do pacote main.


--------------------------------------------------------------------------------------------------------
https://github.com/nmoya/SpinLocks

Interpretei o exerc�cio da seguinte forma: 
"Design an isLocked() method that tests whether ANY thread is holding a lock."
Qualquer thread estiver com o lock, o m�todo retorna true. O teste n�o � feito para uma thread em espec�fico.


A ideia do c�digo � ter nThreads incrementando uma vari�vel 'global_counter' de forma concorrente enquanto uma outra thread observa as threads pegando e soltando os locks.
Uma thread � instanciada recebendo um identificador �nico e um m�todo de lock. 
A thread testa seu atributo 'lockMethod' para saber qual m�todo 'lock()' ela dever� executar. As op��es s�o: Test and Set Lock, CLH lock e MCS lock. O c�digo das threads � extremamente simples: Ela executa N vezes um la�o while. lock, incrementa uma vari�vel global, unlock.

Al�m das 'nThreads', uma thread adicional � instanciada. Esta thread foi denominada 'Watcher', pois ela tem como objetivo, "observar" o mecanismo de lock das demais threads. Ela executa enquanto ainda existir uma outra thread sendo executada. Ela testa se para um determinado mecanismo de lock, se h� alguma thread com o lock adquirido.

O m�todo isLocked() foi implementado nas classes: TASLock.java, MCSLock.java, CLHLock.java.


Test and Set:
Como a vari�vel global 'state' determina o estado da regi�o cr�tica, o m�todo simplesmente verifica esta vari�vel e retorna o valor. Se o valor for TRUE, uma thread est� na RC. Se o valor for false, a RC est� vazia. Isto tamb�m � v�lido para o TTAS lock.


CLHLock:
Como o m�todo virtualiza uma lista, fazendo com que cada thread verifique se o seu predecessor j� saiu da RC antes de entrar, o m�todo isLocked() faz as seguintes verifica��es:
Se a lista estiver vazia, retorne falso.
Sen�o
	� pego o �ltimo n� da fila.
	Se a vari�vel locked estiver falso, � porque ele j� entrou e saiu da RC. Ent�o isLocked() retorna falso.
	Se a vari�vel locked estiver verdadeira, � porque esta thread est� na regi�o cr�tica ou est� esperando outras threads que est�o na sua frente da fila sa�rem. Logo, o m�todo isLocked() retorna verdadeiro.


MCSLock:
Ainda pensando...



