export class Participant {
  constructor(
    public id: number,
    public name: string,
    public email: string,
    public sentConfirmationEmail: boolean = false,
    public confirmedConfirmationEmail: boolean = false,
    public sentWishLink: boolean = false,
    public confirmedRecievedWishLink: boolean = false,
    public wishMessageWritten: boolean = false,
    public sentTargetGiftMessage: boolean = false,
    // public confirmedRecievedTargetGiftMessage: boolean = false
  ) { }
}

export class ParticipantCode {
  constructor(
    public code: string
  ) { }
}
