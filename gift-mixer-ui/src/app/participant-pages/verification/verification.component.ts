import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Participant } from 'src/app/admin/model/participant.model';
import { ParticipantsService } from 'src/app/admin/participants/participants.service';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss']
})
export class VerificationComponent implements OnInit {

  participantCode: string;
  participant: Participant;

  successfulVerification = false;

  constructor(
    private titleService: Title,
    private rest: RestServiceComponent,
    private route: ActivatedRoute,
    private participantsService: ParticipantsService,
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('Potrditev');
    this.route.params.subscribe(params => {

      this.participantCode = params.participantCode;

      this.participantsService.fetchParticipantByCode(this.participantCode).subscribe(participant => {
        this.participant = participant;
      });

      this.verifyParticipantCode();
    });

    this.loadConfetti();
  }

  verifyParticipantCode(): void {
    console.log('Verification');

    this.rest.send('/verifyUser', this.participantCode).subscribe(
      () => {
        console.log('Successful verification');
        this.successfulVerification = true;
      },
      (error) => {
        console.log('Unsuccessful verification');
        this.successfulVerification = false;
      },
    );
  }

  loadConfetti(): void {
    setTimeout(() => {

      class ConfettiParticle {
        constructor(private w: number, private h: number, private maxConfettos: number) {
          this.x = Math.random() * w; // x position
          this.y = Math.random() * h - h; // y position
          this.r = ConfettiParticle.randomFromTo(11, 33); // radius
          this.d = Math.random() * maxConfettos + 11; // density
          this.color =
            ConfettiParticle.possibleColors[
              Math.floor(Math.random() * ConfettiParticle.possibleColors.length)
              ];
          this.tilt = Math.floor(Math.random() * 33) - 11;
          this.tiltAngleIncremental = Math.random() * 0.07 + 0.05;
          this.tiltAngle = 0;
        }

        private static possibleColors: string[] = [
          'DodgerBlue',
          'OliveDrab',
          'Gold',
          'Pink',
          'SlateBlue',
          'LightBlue',
          'Gold',
          'Violet',
          'PaleGreen',
          'SteelBlue',
          'SandyBrown',
          'Chocolate',
          'Crimson',
        ];
        x: number;
        y: number;
        r: number;
        d: number;
        color: string;
        tilt: number;
        tiltAngleIncremental: number;
        tiltAngle: number;

        static randomFromTo(from: number, to: number): number {
          return Math.floor(Math.random() * (to - from + 1) + from);
        }

        draw(ctx: CanvasRenderingContext2D): void {
          ctx.beginPath();
          ctx.lineWidth = this.r / 2;
          ctx.strokeStyle = this.color;
          ctx.moveTo(this.x + this.tilt + this.r / 3, this.y);
          ctx.lineTo(this.x + this.tilt, this.y + this.tilt + this.r / 5);
          ctx.stroke();
        }
      }

      let W = window.innerWidth;
      let H = window.innerHeight;
      const canvas = document.getElementById('canvas') as HTMLCanvasElement;
      const context = canvas.getContext('2d') as CanvasRenderingContext2D;
      const maxConfettis = 150;
      const particles: ConfettiParticle[] = [];

      window.addEventListener(
        'resize',
        () => {
          W = window.innerWidth;
          H = window.innerHeight;
          canvas.width = W;
          canvas.height = H;
        },
        false
      );

      for (let i = 0; i < maxConfettis; i++) {
        particles.push(new ConfettiParticle(W, H, maxConfettis));
      }

      function Draw(): void {
        // Recursive animation
        requestAnimationFrame(Draw);

        // Clear canvas
        context.clearRect(0, 0, W, H);

        // Draw particles
        for (const particle of particles) {
          particle.draw(context);
        }

        // Update particle positions
        particles.forEach((particle, i) => {
          particle.tiltAngle += particle.tiltAngleIncremental;
          particle.y += (Math.cos(particle.d) + 3 + particle.r / 2) / 2;
          particle.tilt = Math.sin(particle.tiltAngle - i / 3) * 15;

          // Reset position if out of bounds
          if (particle.x > W + 30 || particle.x < -30 || particle.y > H) {
            particle.x = Math.random() * W;
            particle.y = -30;
            particle.tilt = Math.floor(Math.random() * 10) - 20;
          }
        });
      }

// Initialize canvas
      canvas.width = W;
      canvas.height = H;

// Start animation
      Draw();

    }, 1000);
  }
}
