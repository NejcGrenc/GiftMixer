export class GiftMixerAdmin {
  constructor(
    public id: number,
    public username: string,
    public alreadySentAllEmailValidation: boolean,
    public alreadySentAllWishLinks: boolean,
    public alreadySentAllTargetGiftMessages: boolean,
    public emailValidationTemplate: string,
    public wishLinkTemplate: string,
    public targetGiftMessageTemplate: string,
  ) {}
}
