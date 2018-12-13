class Node{

    int currency = 0; //通貨量
    int makeBlocks = 0; //作成したブロック数
    int importance = 0; //ノードの信頼度

    public Node(int currency) {
        this.currency = currency;
    }

    /** 
     *ブロックを作成するメソッド
    */
    public void makeBlock(){
        setMakeBlocks(makeBlocks++);
    }

    public int getMakeBlocks() {
        return this.makeBlocks;
    }

    public void setMakeBlocks(int makeBlocks) {
        this.makeBlocks = makeBlocks;
    }

    public int getCurrency() {
        return this.currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getImportance() {
        return this.importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}