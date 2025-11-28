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
    public error: string,
    public pairs: GiverRecieverPair[]
  ) { }
}

export class ChainRule {
  constructor(
    public id: number,
    public giverId: number,
    public receiverId: number,
  ) { }
}
