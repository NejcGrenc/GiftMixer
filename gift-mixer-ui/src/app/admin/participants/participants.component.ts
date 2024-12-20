import {ParticipantsService} from './participants.service';
import {SingleMessagePopupComponent} from './single-message-popup/single-message-popup.component';
import {PrivateDataPopupComponent} from './private-data-popup/private-data-popup.component';
import {Component, Input, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';
import {Participant} from '../model/participant.model';
import {EmailSenderService} from '../email-sender-popup/email-sender.service';
import {
  SingleTestMessagePopupComponent
} from './single-test-message-popup/single-test-message-popup.component';

@Component({
  selector: 'app-participants',
  templateUrl: './participants.component.html',
  styleUrls: ['./participants.component.scss']
})
export class ParticipantsComponent implements OnInit {

  @Input() adminId: number;

  get participants(): Participant[] {
    return this.participantsDataSource.data;
  }

  displayedColumns: string[] = [
    'name',
    'email',
    'sentConfirmationEmail',
    'confirmedConfirmationEmail',
    'sentWishLink',
    'confirmedRecievedWishLink',
    'wishMessageWritten',
    'sentTargetGiftMessage',
    // 'confirmedRecievedTargetGiftMessage',
    'edit',
    'send',
    'private',
    'testEmailAdmin'];
  participantsDataSource = new MatTableDataSource<Participant>([]);

  isEditEnabled = false;
  participantForm: FormGroup;


  constructor(
    public dialog: MatDialog,
    private participantsService: ParticipantsService,
    private emailSenderService: EmailSenderService
  ) {
  }

  ngOnInit(): void {
    this.resetParticipantForm();
    this.participantsService.fetchParticipants().subscribe(participants => {
      this.participantsDataSource.data = participants;
      this.refreshParticipantTable();
    });
  }


  private resetParticipantForm(): void {
    this.participantForm = new FormGroup({
      idFormControl: new FormControl(-1),
      nameFormControl: new FormControl('', Validators.required),
      emailFormControl: new FormControl('', [Validators.required, Validators.email]),
    });
    this.isEditEnabled = false;
  }

  public editParticipnatSetFormData(participant: Participant): void {
    this.participantForm.get('idFormControl').setValue(participant.id);
    this.participantForm.get('nameFormControl').setValue(participant.name);
    this.participantForm.get('emailFormControl').setValue(participant.email);
    this.isEditEnabled = true;
  }

  public submitCreateParticipant(): void {
    const name = this.participantForm.get('nameFormControl').value;
    const email = this.participantForm.get('emailFormControl').value;
    this.createParticipantWithNameAndEmail(name, email);
  }

  public createParticipantWithNameAndEmail(name: string, email: string): void {
    this.participantsService.newParticipant(name, email).subscribe(newParticipant => {
      this.addParticipant(newParticipant);
      this.refreshParticipantTable();
    });
    this.resetParticipantForm();
  }

  public submitEditParticipant(): void {
    const id = this.participantForm.get('idFormControl').value;
    const name = this.participantForm.get('nameFormControl').value;
    const email = this.participantForm.get('emailFormControl').value;

    const participant = this.findParticipantById(id);
    participant.name = name;
    participant.email = email;

    this.participantsService.editParticipant(participant).subscribe(editedParticipant => {
      this.removeParticipant(participant);
      this.addParticipant(editedParticipant);
      this.refreshParticipantTable();
    });

    this.resetParticipantForm();
  }

  public submitRemoveParticipant(): void {
    const id = this.participantForm.get('idFormControl').value;

    const participant = this.findParticipantById(id);
    this.participantsService.removeParticipant(id).subscribe(() => {
      this.removeParticipant(participant);
      this.refreshParticipantTable();
    });

    this.resetParticipantForm();
  }

  public cancelEditParticipant(): void {
    this.resetParticipantForm();
    this.refreshParticipantTable();
  }

  private findParticipantById(id: number): Participant {
    return this.participantsDataSource.data.find(x => x.id === id);
  }

  private addParticipant(participant: Participant): void {
    this.participantsDataSource.data.push(participant);
  }

  private removeParticipant(participant: Participant): void {
    this.participantsDataSource.data = this.participantsDataSource.data.filter(x => x !== participant);
  }

  private refreshParticipantTable(): void {
    this.participantsDataSource.data = this.participantsDataSource.data;
  }


  public sendMailSingle(participant: Participant): void {
    this.dialog.open(SingleMessagePopupComponent, {
      data: {participant}
    });
  }

  public viewPrivateData(participant: Participant): void {
    this.dialog.open(PrivateDataPopupComponent, {
      data: {participant}
    });
  }

  public sendTestEmail(participant: Participant): void {
    console.log('Sending test email to admin');
    this.dialog.open(SingleTestMessagePopupComponent, {
      data: {participant}
    });
  }
}
