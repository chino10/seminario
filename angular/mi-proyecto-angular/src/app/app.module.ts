import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MiPantallaComponent } from './mi-pantalla/mi-pantalla.component';
import { HttpClientModule } from '@angular/common/http';
import { DetalleTramiteComponent } from './component/detalle-tramite/detalle-tramite.component';


@NgModule({
  declarations: [
    AppComponent,
    MiPantallaComponent,
    DetalleTramiteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [DetalleTramiteComponent]
})
export class AppModule { }
