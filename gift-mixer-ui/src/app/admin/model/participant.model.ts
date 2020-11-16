export class Participant {
  constructor(
    public id: number,
    public name: string,
    public email: string,
    public sentConfirmationEmail?: boolean,
    public confirmedConfirmationEmail?: boolean,
    public sentWishLink?: boolean,
    public confirmedRecievedWishLink?: boolean,
    public wishMessageWritten?: boolean,
    public sentTargetGiftMessage?: boolean,
    public confirmedRecievedTargetGiftMessage?: boolean
  ) {
    sentConfirmationEmail = (sentConfirmationEmail !== undefined) ? sentConfirmationEmail : false;
    confirmedConfirmationEmail = (confirmedConfirmationEmail !== undefined) ? confirmedConfirmationEmail : false;
    sentWishLink = (sentWishLink !== undefined) ? sentWishLink : false;
    confirmedRecievedWishLink = (confirmedRecievedWishLink !== undefined) ? confirmedRecievedWishLink : false;
    wishMessageWritten = (wishMessageWritten !== undefined) ? wishMessageWritten : false;
    sentTargetGiftMessage = (sentTargetGiftMessage !== undefined) ? sentTargetGiftMessage : false;
    confirmedRecievedTargetGiftMessage = (confirmedRecievedTargetGiftMessage !== undefined) ? confirmedRecievedTargetGiftMessage : false;
  }
}

export class ParticipantCode {
  constructor(
    public code: string
  ) { }
}

export class LinkInChain {
  constructor(
    public participantId: number,
    public recieverFrom: number,
    public recieverFromName: string,
    public giverTo: number,
    public giverToName: string
  ) { }
}
