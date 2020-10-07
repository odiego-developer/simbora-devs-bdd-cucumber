Feature: Quando uma transação for realizada em uma conta bancária, seja ela envolvendo outra conta bancária ou não,
o saldo das contas em questão deverão ser ajustados conforme a transação

  @Dropped
  Scenario: Quando uma conta receber um valor por depósito,
  o saldo da conta deve ser igual ao saldo atual mais o valor que foi depositado.
    Given Eu crio uma conta sem saldo de numeração 1
    When Eu realizo um depósito de 20.00 euros na conta 1
    Then o saldo da conta 1 deverá ser de 20.00 euros

  @Dropped
  Scenario: Quando uma conta receber um deposito negativo, o saldo da conta não deve ser modificado.
    Given Eu crio uma conta sem saldo de numeração 1
    When Eu realizo um depósito de -20.00 euros na conta 1
    Then o saldo da conta 1 deverá ser de 0 euros

  Scenario Outline: Quando uma conta receber um valor por depósito,
  o saldo da conta deve ser igual ao saldo atual mais o valor que foi depositado.
  Porém, quando o valor depositado for negativo, o saldo atual não pode ser modificado.
  Given Eu crio uma conta sem saldo de numeração <numeroConta>
  When Eu realizo um depósito de <valorDeposito> euros na conta <numeroConta>
  Then o saldo da conta <numeroConta> deverá ser de <saldoEsperado> euros
    Examples:
      | numeroConta | valorDeposito | saldoEsperado |
      | 1           | 20.5          | 20.5          |
      | 2           | -20           | 0             |

  Scenario:  Quando um valor for tranferido de uma conta para outra, o saldo de ambas deverá ser alterado de acordo com valor da transação.
  Given Eu crio as seguintes contas abaixo com saldo
  | 123         | 20         |
  | 1234        | 20         |
  | 12345       | 20         |
  And Eu crio uma conta sem saldo de numeração 321
  When As contas criadas anteriormente transferem 20.0 euros para a conta 321
  Then O saldo das contas abaixo deverão estar
  | 123         | 0          |
  | 1234        | 0          |
  | 12345       | 0          |
  | 321         | 60         |