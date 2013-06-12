#################################
Nikolas Moya - 144678 - Lista 03
#################################

Basta abrir esta pasta 'SpinLocks' como sendo um projeto do Eclipse.

A execução do programa comea em Begin.java, dentro do pacote main.


--------------------------------------------------------------------------------------------------------
https://github.com/nmoya/SpinLocks

Interpretei o exercício da seguinte forma: 
"Design an isLocked() method that tests whether ANY thread is holding a lock."
Qualquer thread estiver com o lock, o método retorna true. O teste não é feito para uma thread em específico.


A ideia do código é ter nThreads incrementando uma variável 'global_counter' de forma concorrente enquanto uma outra thread observa as threads pegando e soltando os locks.
Uma thread é instanciada recebendo um identificador único e um método de lock. 
A thread testa seu atributo 'lockMethod' para saber qual método 'lock()' ela deverá executar. As opções são: Test and Set Lock, CLH lock e MCS lock. O código das threads é extremamente simples: Ela executa N vezes um laço while. lock, incrementa uma variável global, unlock.

Além das 'nThreads', uma thread adicional é instanciada. Esta thread foi denominada 'Watcher', pois ela tem como objetivo, "observar" o mecanismo de lock das demais threads. Ela executa enquanto ainda existir uma outra thread sendo executada. Ela testa se para um determinado mecanismo de lock, se há alguma thread com o lock adquirido.

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
Ainda pensando...



