#################################
Nikolas Moya - 144678 - Lista 03
#################################

Basta abrir esta pasta 'SpinLocks' como sendo um projeto do Eclipse.

A execu���o do programa come��a em Begin.java, dentro do pacote main.


--------------------------------------------------------------------------------------------------------
https://github.com/nmoya/SpinLocks

Interpretei o exerc�cio da seguinte forma: 
"Design an isLocked() method that tests whether ANY thread is holding a lock."
Qualquer thread estiver com o lock, o m�todo retorna true. O teste n�o � feito para uma thread em espec�fico, ao contr�rio do que est� escrito em algumas edi��es do livro.


A ideia do c�digo � ter nThreads tentando entrar numa regi�o cr�tica de forma concorrente enquanto uma outra thread observa as threads pegando e soltando os locks.
Uma thread � instanciada recebendo um identificador �nico e um m�todo de lock. 
A thread testa seu atributo 'lockMethod' para saber qual m�todo 'lock()' ela dever� executar. As op��es s�o: Test and Set Lock, CLH lock e MCS lock. O c�digo das threads � extremamente simples.
Elas dormem por 500ms apenas para esperar a thread observadora ser instanciada, em seguida, elas executam N vezes um la�o while. Dentro do la�o, elas adquirem o lock, dormem por 50ms, fazem unlock.
Thread {
	sleep (500)
	while (i < N)
		lock()
		sleep(50)
		unlock()
}


Al�m das 'nThreads', uma thread adicional � instanciada. Esta thread foi denominada 'Watcher', pois ela tem como objetivo, "observar" o mecanismo de lock das demais threads. Ela executa enquanto ainda existir uma outra thread sendo executada. Ela testa se para um determinado mecanismo de lock, se h� alguma thread com o lock adquirido. Como as threads ao serem lan�adas dormem por 500ms para dar tempo desta thread ser executada, � normal as primeiras execu��es o lock estar liberado. Depois de algumas itera��es o lock � adquirido e eventualmente todas as threads fazem unlock e a saida � lock liberado.
Resultado esperado:
Free
...
Locked
Locked
....
Free



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
Se uma thread adquirir o lock e nenhuma outra thread estiver esperando, a vari�vel 'tail' recebe NULL novamente. Isso tamb�m acontece, se a �ltima thread de uma fila de threads fizer unlock. Logo, se a vari�vel 'tail' estiver NULL, n�o h� ningu�m na fila e o m�todo retorna false. Se 'tail' estiver apontando para algum n�, ent�o este n� est� na regi�o cr�tica ou esperando uma outra thread sair poder entrar, neste caso, isLocked retorna True.




