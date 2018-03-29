import java.util.ArrayList;

class DagNode{
    public ArrayList<Block> chain = new ArrayList<>(); //ブロックのチェーン
    private Block inspectBlock = null; //検査されるブロックを保持する
    private int numberCount = 1;//BlockのData内容(ブロックのIDカウントみたいなの)
    private boolean inspectRun = false;//inspectメソッドを実行したかどうか判定(Blockを追加するとfalseに戻る)

    //ノードが持つチェーンにブロックを追加。ブロック受信側が利用
    public void addBlock(){
        //追加するブロックが1番目
        if(chain.isEmpty()){
            chain.add(inspectBlock);//チェーンにブロックを追加する
        }
        //追加するブロックが2番目以降
        else{
            Block preBlock = chain.get(chain.size() - 1);//チェーンの最後に入っているブロックを呼び出す
            inspectBlock.setPrev(preBlock);//追加するブロックに前のブロックを入れる
            chain.add(inspectBlock);//チェーンにブロックを追加する
        }
        inspectRun = false;//ブロック追加した時inspectRunはfalseになる
    }

    //チェーンの長さを返す
    public int getChainlength(){
        return chain.size();
    }

    //ブロックの検査メソッド(実際に検査をするわけではない.実際はブロックにデータを追加する)
    //BlockDAGにおいてここに処理を追加する必要がある!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void inspect(Block block){
        inspectBlock = block; //検査ブロックとしてノードが保持する
        inspectBlock.setData(numberCount); //検査ブロックにデータを挿入する
        numberCount++;//カウントを増やす
        inspectRun = true;//inspectを実行したのでTrueに
    }

    //getInspectRunの値を返す
    public boolean getInspectRun(){
        return inspectRun;
    }
}