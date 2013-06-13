#################################
Nikolas Moya - 144678 - Lista 03
#################################

Basta abrir esta pasta 'SpinLocks' como sendo um projeto do Eclipse.

A execução do programa começa em Begin.java, dentro do pacote main.


--------------------------------------------------------------------------------------------------------
https://github.com/nmoya/SpinLocks

Interpretei o exercício da seguinte forma: 
"Design an isLocked() method that tests whether ANY thread is holding a lock."
Qualquer thread estiver com o lock, o método retorna true. O teste não é feito para uma thread em específico, ao contrário do que está escrito em algumas edições do livro.


A ideia do código é ter nThreads tentando entrar numa região crítica de forma concorrente enquanto uma outra thread observa as threads pegando e soltando os locks.
Uma thread é instanciada recebendo um identificador único e um método de lock. 
A thread testa seu atributo 'lockMethod' para saber qual método 'lock()' ela deverá executar. As opções são: Test and Set Lock, CLH lock e MCS lock. O código das threads é extremamente simples.
Elas dormem por 500ms apenas para esperar a thread observadora ser instanciada, em seguida, elas executam N vezes um laço while. Dentro do laço, elas adquirem o lock, dormem por 50ms, fazem unlock.
Thread {
	sleep (500)
	while (i < N)
		lock()
		sleep(50)
		unlock()
}


Além das 'nThreads', uma thread adicional é instanciada. Esta thread foi denominada 'Watcher', pois ela tem como objetivo, "observar" o mecanismo de lock das demais threads. Ela executa enquanto ainda existir uma outra thread sendo executada. Ela testa se para um determinado mecanismo de lock, se há alguma thread com o lock adquirido. Como as threads ao serem lançadas dormem por 500ms para dar tempo desta thread ser executada, é normal as primeiras execuções o lock estar liberado. Depois de algumas iterações o lock é adquirido e eventualmente todas as threads fazem unlock e a saida é lock liberado.
Resultado esperado:
Free
...
Locked
Locked
....
Free



O método isLocked() foi implementado nas classes: TASLock.java, MCSLock.java, CLHLock.java.


Test and Set:
Como a variável global 'state' determina o estado da região crítica, o método simplesmente verifica esta variável e retorna o valor. Se o valor for TRUE, uma thread está na RC. Se o valor for false, a RC está vazia. Isto também é válido para o TTAS lock.


CLHLock:
Como o método virtualiza uma lista, fazendo com que cada thread verifique se o seu predecessor já saiu da RC antes de entrar, o método isLocked() faz as seguintes verificações:
Se a lista estiver vazia, retorne falso.
Senão
	É pego o último nó da fila.
	Se a variável locked estiver falso, é porque ele já entrou e saiu da RC. Então isLocked() retorna falso.
	Se a variável locked estiver verdadeira, é porque esta thread está na região crítica ou está esperando outras threads que estão na sua frente da fila saírem. Logo, o método isLocked() retorna verdadeiro.


MCSLock:
Se uma thread adquirir o lock e nenhuma outra thread estiver esperando, a variável 'tail' recebe NULL novamente. Isso também acontece, se a última thread de uma fila de threads fizer unlock. Logo, se a variável 'tail' estiver NULL, não há ninguém na fila e o método retorna false. Se 'tail' estiver apontando para algum nó, então este nó está na região crítica ou esperando uma outra thread sair poder entrar, neste caso, isLocked retorna True.




