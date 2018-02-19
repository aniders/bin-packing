import 'core-js';
import 'zone.js/dist/zone';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app.module';

import {AppComponent} from './app.component';

platformBrowserDynamic().bootstrapModule(AppModule);
