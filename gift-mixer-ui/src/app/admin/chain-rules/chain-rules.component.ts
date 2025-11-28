import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Participant } from '../model/participant.model';
import { ChainRule } from '../model/chain.model';
import { Observable } from 'rxjs';
import { RestServiceComponent } from '../../rest-service/rest-service.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ParticipantsService } from '../participants/participants.service';

@Component({
  selector: 'app-chain-rules',
  templateUrl: './chain-rules.component.html',
  styleUrls: ['./chain-rules.component.scss']
})
export class ChainRulesComponent implements OnInit {

  participants: Participant[];

  displayedColumns: string[] = [
    'giver',
    'ruleType',
    'receiver',
    'actions'
  ];

  chainRules: ChainRule[] = null;
  dataSource = new MatTableDataSource<ChainRule>(this.chainRules);

  chainRuleForm: FormGroup;
  errorOnAdd = '';

  constructor(
    private participantsService: ParticipantsService,
    private rest: RestServiceComponent
  ) {
    this.chainRuleForm = new FormGroup({
      giverFormControl:  new FormControl('', Validators.required),
      receiverFormControl: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.participantsService.fetchParticipants().subscribe(participants => {
      this.participants = participants;
    });
    this.loadChainRules();
  }


  loadChainRules(): void {
    this._loadChainRules().subscribe(chainRules => {
      this.chainRules = chainRules;
      this.dataSource.data = chainRules;
    });
  }
  private resetForm(): void {
    this.chainRuleForm.reset();
    this.chainRuleForm.markAsPristine();
  }


  addChainRule(giver: Participant, receiver: Participant): void {
    this._addChainRule(
      giver.id,
      receiver.id
    ).subscribe(() => this.loadChainRules());
    this.resetForm();
  }

  removeChainRule(ruleId: number): void {
    this._removeChainRule(ruleId).subscribe(() => this.loadChainRules());
  }


  public submitAddRule(): void {
    try {
      this.errorOnAdd = '';

      const giver = this.chainRuleForm.get('giverFormControl').value;
      const receiver = this.chainRuleForm.get('receiverFormControl').value;
      const giverParticipant = this.findParticipantByName(giver);
      const receiverParticipant = this.findParticipantByName(receiver);
      if (!giverParticipant) { throw Error('Neveljavni dajalec'); }
      if (!receiverParticipant) { throw Error('Neveljavni prejemnik'); }

      this._addChainRule(giverParticipant.id, receiverParticipant.id).subscribe(() => this.loadChainRules());
      this.resetForm();
    } catch (e) {
      this.errorOnAdd = 'Neznan problem: ' + e.message;
    }
  }

  private findParticipantById(id: number): Participant {
    return this.participants.find(x => x.id === id);
  }
  private findParticipantByName(name: string): Participant {
    return this.participants.find(x => x.name === name);
  }

  /* Movve this to a separate service */

  _loadChainRules(): Observable<ChainRule[]> {
    return this.rest.fetch<ChainRule[]>('/getChainRules', null);
  }

  _addChainRule(giverId: number, receiverId: number): Observable<ChainRule> {
    return this.rest.fetch<ChainRule>('/addBlockingChainRule', {giverId, receiverId});
  }

  _removeChainRule(ruleId: number): Observable<void> {
    return this.rest.send('/removeChainRule', ruleId);
  }


}
