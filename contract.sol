 solidity ^0.4.21;

contract Game {
    address public firstPlayer;
    address public secondPlayer;
    
    uint public balanceFirstPlayer;
    uint public balanceSecondPlayer;
    
    bool gameFinished = false;
    
    function Game(address _firstPlayer, address _secondPlayer) public {
        firstPlayer = _firstPlayer;
        secondPlayer = _secondPlayer;
        
    }
    
    function deposit(uint firstPlayerDeposit, uint secondPlayerDeposit) public {
        balanceFirstPlayer += firstPlayerDeposit;
        balanceSecondPlayer += secondPlayerDeposit;
        
    }
    
    function endOfRound(address winner, uint loserAmount) public{
        if (winner == firstPlayer){
            balanceFirstPlayer += loserAmount;
            balanceSecondPlayer -= loserAmount;
            
        } else {
            balanceFirstPlayer -= loserAmount;
            balanceSecondPlayer += loserAmount;
        }
    }
    
    function setGameState (bool _State) public {
        gameFinished = _State;
    }
    
    function getGameState () public view returns (bool){
        return gameFinished;
    }
    
    function withdraw() public payable{
        firstPlayer.transfer(balanceFirstPlayer);
        secondPlayer.transfer(balanceSecondPlayer);
    }
    
    function getFirstPlayerBalance() public view returns (uint) {
        return balanceFirstPlayer;
    }
    
    function getSecondPlayerBalance() public view returns (uint) {
        return balanceSecondPlayer;
    }
    
    function getFirstPlayerAddress () public view returns (address) {
        return firstPlayer;
    }
    
    function getSecondPlayerAddress () public view returns (address) {
        return secondPlayer;
    }
    
}
