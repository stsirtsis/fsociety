import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule, MatButtonModule, MatGridListModule, MatToolbarModule } from '@angular/material';

@NgModule({
  imports: [MatButtonModule,
    MatGridListModule,
    MatIconModule,
    MatToolbarModule
  ],
  exports: [MatButtonModule,
    MatGridListModule,
    MatIconModule,
    MatToolbarModule
  ],
})
export class MaterialModule{}
