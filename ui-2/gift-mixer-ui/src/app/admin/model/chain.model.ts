export class GiverRecieverPair {
  constructor(
    public giverId: number,
    public receiverId: number,
    public giverName: string,
    public receiverName: string
  ) { }
}

export class Chain {
  constructor(
    public pairs: GiverRecieverPair[]
  ) { }
}
