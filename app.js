/**
 * Professional Android Office — Frontend Core Logic & State Management
 * Follows reja.txt specifications:
 * - Engine independence simulation
 * - Virtualized Grid calculation & Sheet formula evaluation
 * - PDF Canvas Annotation layer (Drawing, Highlighting, Signature)
 * - Autosave Debouncing (2-5 sec) & Crash Recovery simulation
 * - Storage Access Framework (SAF) URI simulations
 */

// Application State
const AppState = {
  currentScreen: 'screen-home',
  currentLang: 'uz',
  activeDocument: {
    name: 'Kompaniya_Hisoboti_2026.docx',
    type: 'DOCX',
    isDirty: false,
    content: ''
  },
  deviceMode: 'desktop', // 'desktop' or 'mobile'
  theme: 'dark',
  undoStack: [],
  redoStack: [],
  activeCell: 'A1',
  selectedTool: 'select',
  isDrawing: false
};

// 3 Tillik Lug'at (UZ, RU, EN) - reja.txt 47-bo'lim Localization
const I18N = {
  uz: {
    navHome: "Asosiy panel",
    navDocx: "DOCX Tahrirchi",
    navXlsx: "XLSX Grid (Excel)",
    navPdf: "PDF & Annotatsiya",
    navStorage: "SAF Fayl Tizimi",
    heroTitle: "Professional Android Office",
    heroSub: "LibreOffice Core & PDFium integratsiyasi bilan to'liq offline muharrir",
    btnNewDocx: "+ Yangi Word DOCX",
    btnOpenSaf: "SAF orqali ochish",
    newDocTitle: "Yangi Hujjat Yaratish",
    recentTitle: "Oxirgi Fayllar (Recent Files)",
    recentSub: "Room Database keshidagi lokal hujjatlar",
    filterAll: "Barchasi",
    save: "Saqlash",
    savedMsg: "Saqlandi (Autosave faol)",
    savingMsg: "Saqlanmoqda...",
    searchPlaceholder: "Hujjatlar, matnlar yoki formulalardan qidirish...",
    toastLangChanged: "Til o'zgartirildi: O'zbekcha",
    // Creation cards
    cardDocxTitle: "Hujjat (DOCX)",
    cardDocxSub: "Hisobotlar, arizalar va maqolalar",
    cardXlsxTitle: "Jadval (XLSX)",
    cardXlsxSub: "Budjet, formulalar va virtual grid",
    cardPdfTitle: "PDF O'quvchi",
    cardPdfSub: "PDFium rendering & chizish/belgilash",
    // DOCX Tabs & Content
    tabHome: "Bosh sahifa",
    tabInsert: "Qo'yish",
    tabLayout: "Sahifa tuzilishi",
    tabView: "Ko'rinish",
    docxHeading1: "O'zbekiston Respublikasi Innovatsion Loyiha Rejasi",
    docxHeading2: "1. Arxitektura Asoslari va LibreOfficeKit",
    docxHeading3: "2. Belgilangan Bosqichlar Jadvali",
    docxP1: "Hujjat turi: Professional Android Office Platformasi (DOCX Engine)",
    docxP2: "Mazkur hujjat reja.txt da ko'rsatilgan Clean Architecture va Engine mustaqilligi prinsiplari asosida render qilinmoqda. Kotlin JNI orqali LibreOfficeKit bilan bog'langan holatda 100% oflayn formatda ishlaydi.",
    docxRuleTitle: "Muhim Qoida (reja.txt 2.4):",
    docxRuleP: "Katta 100MB DOCX fayllar ochilganda butun fayl birdan RAM xotiraga yuklanmaydi, balki SurfaceView va Tile Rendering orqali fragmentma-fragment chiziladi.",
    pageMeta: "Sahifa 1 / 3 • 142 so'z • 100% Zoom",
    // XLSX
    sheetTab1: "Varaq 1 (Budjet)",
    sheetTab2: "Varaq 2 (Prognoz)",
    sumSummary: "Tanlangan yig'indisi: ",
    formulaHint: "Formulani kiriting (masalan: =SUM(B2:B5) yoki oddiy qiymat)",
    // PDF
    toolSelect: "Tanlash",
    toolHighlight: "Highlight",
    toolPen: "Qalam",
    toolNote: "Izoh",
    toolSign: "Imzo",
    pdfGovBadge: "O'ZBEKISTON RESPUBLIKASI STANDARTI",
    pdfDocTitle: "XIZMAT HUJJATI № 9482-B",
    pdfDate: "Sana: 07-Sentabr, 2026-yil",
    pdfBody1: "Mazkur hujjat Android Office arxitekturasida PDFium kutubxonasining kross-kompilyatsiyasi va sahifalarni keshlab bitmap ko'rinishida chiqarish prinsiplarini tasdiqlaydi.",
    pdfBody2: "Foydalanuvchi hujjat ustiga erkin chizishi, matnlarni sariq rang bilan belgilashi va pastdagi ruxsat qismiga o'zining tasdiq imzosini kiritishi mumkin. Hamma annotatsiyalar alohida qatlamda (Annotation Layer) saqlanadi.",
    pdfSigText: "Imzo maydoni (Ustiga bosing yoki imzo qo'ying)",
    pdfSigStamp: "✓ TASDIQLANDI - 2026",
    // SAF
    safTitle: "Xavfsiz Fayl Tizimi",
    safSub: "reja.txt 7 & 28-bo'limlar: To'liq ruxsatsiz, faqat foydalanuvchi tanlagan fayllar bilan content:// URI orqali ishlash.",
    safOpenTitle: "ACTION_OPEN_DOCUMENT",
    safOpenSub: "Telefon xotirasidan istalgan DOCX, XLSX yoki PDF faylni tanlash",
    safCreateTitle: "ACTION_CREATE_DOCUMENT",
    safCreateSub: "Yangi bo'sh hujjat yaratish va tizimga doimiy permission olish",
    safTreeTitle: "ACTION_OPEN_DOCUMENT_TREE",
    safTreeSub: "Butun jildga ruxsat olish (takePersistableUriPermission)",
    safBtnAction1: "Fayl tanlash",
    safBtnAction2: "Yangi yaratish",
    safBtnAction3: "Jildni tanlash",
    safTableTitle: "Faol Doimiy Ruxsatlar (takePersistableUriPermission)",
    // Footer & Device
    modeLabel: "Qurilma rejimi:",
    themeDark: "Tungi rejim",
    themeLight: "Kunduzgi rejim"
  },
  ru: {
    navHome: "Главная панель",
    navDocx: "Редактор DOCX",
    navXlsx: "Таблица XLSX",
    navPdf: "PDF и Заметки",
    navStorage: "Файлы SAF",
    heroTitle: "Профессиональный Android Office",
    heroSub: "Полностью автономный офис на базе LibreOffice Core и PDFium",
    btnNewDocx: "+ Новый Word DOCX",
    btnOpenSaf: "Открыть через SAF",
    newDocTitle: "Создать документ",
    recentTitle: "Недавние файлы",
    recentSub: "Локальные документы в кэше Room Database",
    filterAll: "Все",
    save: "Сохранить",
    savedMsg: "Сохранено (Автосохранение)",
    savingMsg: "Сохранение...",
    searchPlaceholder: "Поиск документов, текста или формул...",
    toastLangChanged: "Язык интерфейса: Русский",
    // Creation cards
    cardDocxTitle: "Документ (DOCX)",
    cardDocxSub: "Отчеты, заявления и статьи",
    cardXlsxTitle: "Таблица (XLSX)",
    cardXlsxSub: "Бюджет, формулы и виртуальная сетка",
    cardPdfTitle: "Просмотр PDF",
    cardPdfSub: "Отрисовка PDFium, заметки и подпись",
    // DOCX Tabs & Content
    tabHome: "Главная",
    tabInsert: "Вставка",
    tabLayout: "Разметка",
    tabView: "Вид",
    docxHeading1: "План инновационного проекта Республики Узбекистан",
    docxHeading2: "1. Основы архитектуры и LibreOfficeKit",
    docxHeading3: "2. Таблица этапов реализации",
    docxP1: "Тип документа: Профессиональная платформа Android Office (DOCX Engine)",
    docxP2: "Данный документ отображается в соответствии с принципами чистой архитектуры и независимости движка, описанными в reja.txt. Работает на 100% оффлайн через Kotlin JNI и LibreOfficeKit.",
    docxRuleTitle: "Критическое правило (reja.txt 2.4):",
    docxRuleP: "При открытии больших файлов DOCX (100 МБ) весь файл не загружается целиком в RAM. Вместо этого он отображается фрагментами через SurfaceView и Tile Rendering.",
    pageMeta: "Страница 1 из 3 • 142 слова • 100% Масштаб",
    // XLSX
    sheetTab1: "Лист 1 (Бюджет)",
    sheetTab2: "Лист 2 (Прогноз)",
    sumSummary: "Сумма выбранного: ",
    formulaHint: "Введите формулу (напр.: =SUM(B2:B5) или значение)",
    // PDF
    toolSelect: "Выделение",
    toolHighlight: "Маркер",
    toolPen: "Перо",
    toolNote: "Заметка",
    toolSign: "Подпись",
    pdfGovBadge: "СТАНДАРТ РЕСПУБЛИКИ УЗБЕКИСТАН",
    pdfDocTitle: "СЛУЖЕБНЫЙ АКТ № 9482-B",
    pdfDate: "Дата: 07 сентября 2026 г.",
    pdfBody1: "Данный документ подтверждает кросс-компиляцию библиотеки PDFium и вывод страниц в виде кэшированных растровых изображений на Android.",
    pdfBody2: "Пользователь может свободно рисовать поверх документа, выделять текст желтым маркером и ставить электронную подпись в нижней части. Все аннотации хранятся в отдельном слое.",
    pdfSigText: "Область подписи (нажмите для подписи)",
    pdfSigStamp: "✓ ПОДТВЕРЖДЕНО - 2026",
    // SAF
    safTitle: "Безопасная файловая система",
    safSub: "Разделы 7 и 28 reja.txt: Без лишних разрешений, работа только с выбранными файлами через content:// URI.",
    safOpenTitle: "ACTION_OPEN_DOCUMENT",
    safOpenSub: "Выбор любого файла DOCX, XLSX или PDF из памяти телефона",
    safCreateTitle: "ACTION_CREATE_DOCUMENT",
    safCreateSub: "Создание нового пустого документа с постоянным разрешением",
    safTreeTitle: "ACTION_OPEN_DOCUMENT_TREE",
    safTreeSub: "Доступ к целой папке (takePersistableUriPermission)",
    safBtnAction1: "Выбрать файл",
    safBtnAction2: "Создать новый",
    safBtnAction3: "Выбрать папку",
    safTableTitle: "Активные постоянные разрешения (takePersistableUriPermission)",
    // Footer & Device
    modeLabel: "Режим устройства:",
    themeDark: "Тёмная тема",
    themeLight: "Светлая тема"
  },
  en: {
    navHome: "Home Dashboard",
    navDocx: "DOCX Editor",
    navXlsx: "XLSX Grid (Excel)",
    navPdf: "PDF & Annotations",
    navStorage: "SAF File System",
    heroTitle: "Professional Android Office",
    heroSub: "Fully offline office editor powered by LibreOffice Core & PDFium",
    btnNewDocx: "+ New Word DOCX",
    btnOpenSaf: "Open via SAF",
    newDocTitle: "Create New Document",
    recentTitle: "Recent Files",
    recentSub: "Local cached files stored in Room Database",
    filterAll: "All",
    save: "Save",
    savedMsg: "Saved (Autosave active)",
    savingMsg: "Saving...",
    searchPlaceholder: "Search documents, text or formulas...",
    toastLangChanged: "Language changed: English",
    // Creation cards
    cardDocxTitle: "Document (DOCX)",
    cardDocxSub: "Reports, letters and articles",
    cardXlsxTitle: "Spreadsheet (XLSX)",
    cardXlsxSub: "Budgets, formulas and virtual grid",
    cardPdfTitle: "PDF Reader",
    cardPdfSub: "PDFium rendering, drawing & signing",
    // DOCX Tabs & Content
    tabHome: "Home",
    tabInsert: "Insert",
    tabLayout: "Layout",
    tabView: "View",
    docxHeading1: "Republic of Uzbekistan Innovation Project Plan",
    docxHeading2: "1. Architecture Foundations & LibreOfficeKit",
    docxHeading3: "2. Milestone Implementation Table",
    docxP1: "Document Type: Professional Android Office Platform (DOCX Engine)",
    docxP2: "This document is rendered following Clean Architecture and Engine Independence principles specified in reja.txt. Operating 100% offline via Kotlin JNI and LibreOfficeKit.",
    docxRuleTitle: "Critical Rule (reja.txt 2.4):",
    docxRuleP: "When opening large 100MB DOCX files, the file is not loaded entirely into RAM. Instead, it is rendered in fragments via SurfaceView and Tile Rendering.",
    pageMeta: "Page 1 of 3 • 142 words • 100% Zoom",
    // XLSX
    sheetTab1: "Sheet 1 (Budget)",
    sheetTab2: "Sheet 2 (Forecast)",
    sumSummary: "Selected sum: ",
    formulaHint: "Enter formula (e.g. =SUM(B2:B5) or raw value)",
    // PDF
    toolSelect: "Select",
    toolHighlight: "Highlight",
    toolPen: "Pen",
    toolNote: "Note",
    toolSign: "Sign",
    pdfGovBadge: "REPUBLIC OF UZBEKISTAN STANDARD",
    pdfDocTitle: "OFFICIAL ACT № 9482-B",
    pdfDate: "Date: September 07, 2026",
    pdfBody1: "This document verifies the cross-compilation of the PDFium library and cached bitmap page rendering on Android.",
    pdfBody2: "The user can draw freely on the document, highlight text with a yellow marker, and place an electronic signature below. All annotations are saved in a separate layer.",
    pdfSigText: "Signature area (tap to sign)",
    pdfSigStamp: "✓ VERIFIED - 2026",
    // SAF
    safTitle: "Secure File System (SAF)",
    safSub: "reja.txt Sections 7 & 28: Zero unnecessary permissions, accessing user selected files via content:// URI.",
    safOpenTitle: "ACTION_OPEN_DOCUMENT",
    safOpenSub: "Select any DOCX, XLSX or PDF document from phone storage",
    safCreateTitle: "ACTION_CREATE_DOCUMENT",
    safCreateSub: "Create a new empty document with persistent system permission",
    safTreeTitle: "ACTION_OPEN_DOCUMENT_TREE",
    safTreeSub: "Grant directory access via takePersistableUriPermission",
    safBtnAction1: "Select file",
    safBtnAction2: "Create new",
    safBtnAction3: "Select folder",
    safTableTitle: "Active Persistent Permissions (takePersistableUriPermission)",
    // Footer & Device
    modeLabel: "Device mode:",
    themeDark: "Dark Mode",
    themeLight: "Light Mode"
  }
};

function changeLanguage(lang) {
  if (!I18N[lang]) return;
  AppState.currentLang = lang;
  const t = I18N[lang];

  // 1. Sidebar Navigation
  const navHome = document.querySelector('#nav-btn-home span');
  const navDocx = document.querySelector('#nav-btn-docx span');
  const navXlsx = document.querySelector('#nav-btn-xlsx span');
  const navPdf = document.querySelector('#nav-btn-pdf span');
  const navStorage = document.querySelector('#nav-btn-storage span');
  if (navHome) navHome.textContent = t.navHome;
  if (navDocx) navDocx.textContent = t.navDocx;
  if (navXlsx) navXlsx.textContent = t.navXlsx;
  if (navPdf) navPdf.textContent = t.navPdf;
  if (navStorage) navStorage.textContent = t.navStorage;

  // 2. Sidebar Footer & Mode
  const modeLabel = document.querySelector('.mode-label');
  if (modeLabel) modeLabel.textContent = t.modeLabel;
  const themeText = document.querySelector('.theme-text');
  if (themeText) {
    const isLight = document.body.classList.contains('theme-light');
    themeText.textContent = isLight ? t.themeLight : t.themeDark;
  }

  // 3. Topbar Actions
  const saveBtnSpan = document.querySelector('#global-save-btn span');
  if (saveBtnSpan) saveBtnSpan.textContent = t.save;
  const searchInput = document.getElementById('global-search-input');
  if (searchInput) searchInput.placeholder = t.searchPlaceholder;
  if (saveStatusEl) {
    saveStatusEl.innerHTML = `<span class="status-dot green"></span> ${t.savedMsg}`;
  }

  // 4. Screen 1: Home Dashboard
  const heroH1 = document.querySelector('.hero-content h1');
  const heroP = document.querySelector('.hero-content p');
  const heroBtnDocx = document.querySelector('#hero-create-doc');
  const heroBtnSaf = document.querySelector('#hero-open-saf');
  if (heroH1) heroH1.textContent = t.heroTitle;
  if (heroP) heroP.textContent = t.heroSub;
  if (heroBtnDocx) heroBtnDocx.innerHTML = `<span class="btn-icon">+</span> ${t.btnNewDocx}`;
  if (heroBtnSaf) {
    const svgIcon = heroBtnSaf.querySelector('svg')?.outerHTML || '';
    heroBtnSaf.innerHTML = `${svgIcon} ${t.btnOpenSaf}`;
  }

  // Creation Cards
  const secTitles = document.querySelectorAll('.section-title');
  if (secTitles[0]) secTitles[0].textContent = t.newDocTitle;
  if (secTitles[1]) secTitles[1].textContent = t.recentTitle;
  const secSub = document.querySelector('.section-subtitle');
  if (secSub) secSub.textContent = t.recentSub;

  const cardDocx = document.querySelector('.creation-card.docx-card');
  if (cardDocx) {
    cardDocx.querySelector('h4').textContent = t.cardDocxTitle;
    cardDocx.querySelector('p').textContent = t.cardDocxSub;
  }
  const cardXlsx = document.querySelector('.creation-card.xlsx-card');
  if (cardXlsx) {
    cardXlsx.querySelector('h4').textContent = t.cardXlsxTitle;
    cardXlsx.querySelector('p').textContent = t.cardXlsxSub;
  }
  const cardPdf = document.querySelector('.creation-card.pdf-card');
  if (cardPdf) {
    cardPdf.querySelector('h4').textContent = t.cardPdfTitle;
    cardPdf.querySelector('p').textContent = t.cardPdfSub;
  }

  const fPillAll = document.querySelector('.filter-pills .f-pill');
  if (fPillAll) fPillAll.textContent = t.filterAll;

  // 5. Screen 2: DOCX Editor
  const docxTabs = document.querySelectorAll('.toolbar-tabs .tb-tab');
  if (docxTabs[0]) docxTabs[0].textContent = t.tabHome;
  if (docxTabs[1]) docxTabs[1].textContent = t.tabInsert;
  if (docxTabs[2]) docxTabs[2].textContent = t.tabLayout;
  if (docxTabs[3]) docxTabs[3].textContent = t.tabView;

  const docxPage = document.getElementById('docx-editable-page');
  if (docxPage) {
    const h1 = docxPage.querySelector('h1');
    const pSub = docxPage.querySelector('p');
    const h2 = docxPage.querySelector('h2');
    const pLead = docxPage.querySelectorAll('p')[1];
    const alertBox = docxPage.querySelector('div strong');
    const alertP = docxPage.querySelector('div p');
    const h3 = docxPage.querySelector('h3');

    if (h1) h1.textContent = t.docxHeading1;
    if (pSub) pSub.textContent = t.docxP1;
    if (h2) h2.textContent = t.docxHeading2;
    if (pLead) pLead.textContent = t.docxP2;
    if (alertBox) alertBox.textContent = t.docxRuleTitle;
    if (alertP) alertP.textContent = t.docxRuleP;
    if (h3) h3.textContent = t.docxHeading3;
  }
  const pageCounter = document.querySelector('.page-meta-counter');
  if (pageCounter) pageCounter.textContent = t.pageMeta;

  // 6. Screen 3: XLSX Spreadsheet
  const formulaInput = document.getElementById('formula-input');
  if (formulaInput) formulaInput.placeholder = t.formulaHint;
  const sheetTab1 = document.querySelector('.sheet-tab-item[data-sheet="1"] span');
  const sheetTab2 = document.querySelector('.sheet-tab-item[data-sheet="2"] span');
  if (sheetTab1) sheetTab1.textContent = t.sheetTab1;
  if (sheetTab2) sheetTab2.textContent = t.sheetTab2;

  // 7. Screen 4: PDF Viewer
  const annotBtns = document.querySelectorAll('.annot-tool-btn span');
  if (annotBtns[0]) annotBtns[0].textContent = t.toolSelect;
  if (annotBtns[1]) annotBtns[1].textContent = t.toolHighlight;
  if (annotBtns[2]) annotBtns[2].textContent = t.toolPen;
  if (annotBtns[3]) annotBtns[3].textContent = t.toolNote;
  if (annotBtns[4]) annotBtns[4].textContent = t.toolSign;

  const pdfGovBadge = document.querySelector('.gov-badge');
  const pdfDocTitle = document.querySelector('.pdf-doc-header h2');
  const pdfDate = document.querySelector('.pdf-date');
  const pdfP1 = document.querySelectorAll('.selectable-pdf-text')[0];
  const pdfP2 = document.querySelectorAll('.selectable-pdf-text')[1];
  const pdfSigHolder = document.querySelector('.sig-placeholder');
  const pdfSigStamp = document.querySelector('.sig-stamp');

  if (pdfGovBadge) pdfGovBadge.textContent = t.pdfGovBadge;
  if (pdfDocTitle) pdfDocTitle.textContent = t.pdfDocTitle;
  if (pdfDate) pdfDate.textContent = t.pdfDate;
  if (pdfP1) pdfP1.textContent = t.pdfBody1;
  if (pdfP2) pdfP2.textContent = t.pdfBody2;
  if (pdfSigHolder) pdfSigHolder.textContent = t.pdfSigText;
  if (pdfSigStamp) pdfSigStamp.textContent = t.pdfSigStamp;

  // 8. Screen 5: SAF Storage
  const safHeaderH2 = document.querySelector('.saf-header-panel h2');
  const safHeaderP = document.querySelector('.saf-header-panel p');
  if (safHeaderH2) safHeaderH2.textContent = t.safTitle;
  if (safHeaderP) safHeaderP.textContent = t.safSub;

  const safCards = document.querySelectorAll('.saf-card');
  if (safCards[0]) {
    safCards[0].querySelector('h3').textContent = t.safOpenTitle;
    safCards[0].querySelector('p').textContent = t.safOpenSub;
    safCards[0].querySelector('.saf-btn-action').textContent = t.safBtnAction1;
  }
  if (safCards[1]) {
    safCards[1].querySelector('h3').textContent = t.safCreateTitle;
    safCards[1].querySelector('p').textContent = t.safCreateSub;
    safCards[1].querySelector('.saf-btn-action').textContent = t.safBtnAction2;
  }
  if (safCards[2]) {
    safCards[2].querySelector('h3').textContent = t.safTreeTitle;
    safCards[2].querySelector('p').textContent = t.safTreeSub;
    safCards[2].querySelector('.saf-btn-action').textContent = t.safBtnAction3;
  }

  const safPermH3 = document.querySelector('.saf-perm-section h3');
  if (safPermH3) safPermH3.textContent = t.safTableTitle;

  showToast(t.toastLangChanged, "success");
}

// DOM Elements
const docTitleEl = document.getElementById('current-doc-title');
const docBadgeEl = document.getElementById('current-doc-type-badge');
const saveStatusEl = document.getElementById('doc-save-status');
const deviceWrapper = document.getElementById('device-wrapper');
const navButtons = document.querySelectorAll('.nav-btn');
const toastContainer = document.getElementById('toast-container');

// Initialize App
document.addEventListener('DOMContentLoaded', () => {
  initNavigation();
  initSpreadsheetGrid();
  initPdfCanvas();
  initAutosave();
  initKeyboardShortcuts();
  initThemeAndDeviceControls();

  // Language Selector Listener
  const langSelect = document.getElementById('global-lang-select');
  if (langSelect) {
    langSelect.addEventListener('change', (e) => {
      changeLanguage(e.target.value);
    });
  }

  // Show welcome toast
  setTimeout(() => {
    showToast("Professional Android Office ishga tushirildi! (reja.txt arxitekturasi)", "success");
  }, 400);
});

/* ==========================================================================
   1. NAVIGATION & SCREEN SWITCHING
   ========================================================================== */
function initNavigation() {
  navButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const targetScreen = btn.getAttribute('data-target');
      switchTab(targetScreen);
    });
  });

  // Mobile drawer toggle
  const mobileMenuToggle = document.getElementById('mobile-menu-toggle');
  const sidebar = document.getElementById('main-sidebar');
  if (mobileMenuToggle && sidebar) {
    mobileMenuToggle.addEventListener('click', () => {
      sidebar.classList.toggle('open');
    });
  }
}

function switchTab(screenId) {
  // Update sidebar active states
  navButtons.forEach(btn => {
    if (btn.getAttribute('data-target') === screenId) {
      btn.classList.add('active');
    } else {
      btn.classList.remove('active');
    }
  });

  // Toggle screens
  document.querySelectorAll('.office-screen').forEach(screen => {
    screen.classList.remove('active');
  });

  const target = document.getElementById(screenId);
  if (target) {
    target.classList.add('active');
    AppState.currentScreen = screenId;
  }

  // Update Topbar Document Title & Badge based on context
  if (screenId === 'screen-docx') {
    updateDocHeader('Kompaniya_Hisoboti_2026.docx', 'DOCX', 'var(--docx-blue)');
  } else if (screenId === 'screen-xlsx') {
    updateDocHeader('Choraklik_Budjet_Balansi.xlsx', 'XLSX', 'var(--xlsx-green)');
  } else if (screenId === 'screen-pdf') {
    updateDocHeader('Litsenziya_Shartnomasi_PDFium.pdf', 'PDF', 'var(--pdf-red)');
  } else if (screenId === 'screen-storage') {
    updateDocHeader('Android Storage Explorer (SAF)', 'SAF', '#0284c7');
  } else {
    updateDocHeader('Office Bosh Paneli', 'HOME', 'var(--accent-primary)');
  }
}

function updateDocHeader(title, type, color) {
  if (docTitleEl) docTitleEl.textContent = title;
  if (docBadgeEl) {
    docBadgeEl.textContent = type;
    docBadgeEl.style.backgroundColor = color;
  }
}

function openDocPreset(type) {
  if (type === 'docx') switchTab('screen-docx');
  else if (type === 'xlsx') switchTab('screen-xlsx');
  else if (type === 'pdf') switchTab('screen-pdf');
}

/* ==========================================================================
   2. DOCX EDITOR (reja.txt 14-bo'lim)
   ========================================================================== */
function applyDocStyle(command, value = null) {
  document.execCommand(command, false, value);
  const editableDoc = document.getElementById('docx-editable-page');
  if (editableDoc) editableDoc.focus();
  triggerDocChange();
}

function insertDocTable() {
  const tableHtml = `
    <table class="doc-embedded-table">
      <thead><tr><th>№</th><th>Vazifa Tavsifi</th><th>Holat</th></tr></thead>
      <tbody>
        <tr><td>1</td><td>LibreOfficeKit C++ JNI ulanishi</td><td>Bajarilmoqda</td></tr>
        <tr><td>2</td><td>Storage Access Framework</td><td>Tayyor</td></tr>
      </tbody>
    </table><p></p>
  `;
  document.execCommand('insertHTML', false, tableHtml);
  showToast("Yangi jadval qo'shildi!");
}

function showKeyboard() {
  const doc = document.getElementById('docx-editable-page');
  if (doc) {
    doc.focus();
    showToast("Virtual klaviatura faollashtirildi");
  }
}

/* ==========================================================================
   3. XLSX VIRTUALIZED SPREADSHEET ENGINE (reja.txt 16-17 bo'limlar)
   ========================================================================== */
const SPREADSHEET_COLS = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
const SPREADSHEET_ROW_COUNT = 30; // Virtual window demonstration
const sheetCellData = {};

function initSpreadsheetGrid() {
  const gridBody = document.getElementById('grid-body');
  const formulaInput = document.getElementById('formula-input');
  const activeCellLabel = document.getElementById('active-cell-label');
  if (!gridBody) return;

  gridBody.innerHTML = '';

  // Seed sample accounting values
  const sampleValues = {
    'A1': 'Kategoriya', 'B1': '1-Chorak', 'C1': '2-Chorak', 'D1': 'Jami ($)',
    'A2': 'Ish haqi jamg‘armasi', 'B2': 14500, 'C2': 16200, 'D2': '=B2+C2',
    'A3': 'Server & Hosting (Cloud)', 'B3': 3200, 'C3': 3800, 'D3': '=B3+C3',
    'A4': 'LibreOffice NDK Litsenziya', 'B4': 5000, 'C4': 2500, 'D4': '=B4+C4',
    'A5': 'Marketing & Reklama', 'B5': 4100, 'C5': 5900, 'D5': '=B5+C5',
    'A6': 'Umumiy Xarajatlar', 'B6': '=SUM(B2:B5)', 'C6': '=SUM(C2:C5)', 'D6': '=SUM(D2:D5)'
  };

  Object.assign(sheetCellData, sampleValues);

  // Generate Rows & Cells
  for (let r = 1; r <= SPREADSHEET_ROW_COUNT; r++) {
    const tr = document.createElement('tr');
    
    // Row header (1, 2, 3...)
    const thRow = document.createElement('th');
    thRow.className = 'row-header';
    thRow.textContent = r;
    tr.appendChild(thRow);

    // Columns (A, B, C...)
    SPREADSHEET_COLS.forEach(col => {
      const cellKey = `${col}${r}`;
      const td = document.createElement('td');
      td.className = 'grid-cell';
      td.setAttribute('data-cell', cellKey);
      td.setAttribute('contenteditable', 'true');

      // Populate calculated or raw value
      const rawVal = sheetCellData[cellKey] !== undefined ? sheetCellData[cellKey] : '';
      td.textContent = evaluateFormula(rawVal);

      // Cell interaction
      td.addEventListener('focus', () => {
        selectCell(cellKey);
      });

      td.addEventListener('input', (e) => {
        sheetCellData[cellKey] = td.innerText.trim();
        formulaInput.value = sheetCellData[cellKey];
        recalculateAllSheet();
      });

      tr.appendChild(td);
    });

    gridBody.appendChild(tr);
  }

  // Formula input enter listener
  if (formulaInput) {
    formulaInput.addEventListener('keydown', (e) => {
      if (e.key === 'Enter') {
        const cell = document.querySelector(`.grid-cell[data-cell="${AppState.activeCell}"]`);
        if (cell) {
          sheetCellData[AppState.activeCell] = formulaInput.value;
          recalculateAllSheet();
          cell.focus();
        }
      }
    });

    document.getElementById('btn-apply-formula')?.addEventListener('click', () => {
      const cell = document.querySelector(`.grid-cell[data-cell="${AppState.activeCell}"]`);
      if (cell) {
        sheetCellData[AppState.activeCell] = formulaInput.value;
        recalculateAllSheet();
      }
    });
  }

  selectCell('A1');
}

function selectCell(cellKey) {
  AppState.activeCell = cellKey;
  const activeCellLabel = document.getElementById('active-cell-label');
  const formulaInput = document.getElementById('formula-input');

  if (activeCellLabel) activeCellLabel.textContent = cellKey;
  if (formulaInput) formulaInput.value = sheetCellData[cellKey] || '';

  // Highlight cell
  document.querySelectorAll('.grid-cell.selected').forEach(c => c.classList.remove('selected'));
  const cell = document.querySelector(`.grid-cell[data-cell="${cellKey}"]`);
  if (cell) cell.classList.add('selected');

  updateSheetSelectionSum(cellKey);
}

function evaluateFormula(val) {
  if (typeof val !== 'string' || !val.startsWith('=')) {
    return val;
  }

  try {
    const expr = val.substring(1).toUpperCase();

    // Simple SUM support: =SUM(B2:B5)
    if (expr.startsWith('SUM(') && expr.endsWith(')')) {
      const range = expr.substring(4, expr.length - 1);
      const [start, end] = range.split(':');
      if (start && end) {
        return calculateRangeSum(start, end);
      }
    }

    // Direct addition: =B2+C2
    if (expr.includes('+')) {
      const parts = expr.split('+').map(p => p.trim());
      let sum = 0;
      parts.forEach(p => {
        const cVal = parseFloat(sheetCellData[p] || 0);
        sum += isNaN(cVal) ? 0 : cVal;
      });
      return sum;
    }

    return expr;
  } catch (err) {
    return "#XATO!";
  }
}

function calculateRangeSum(startKey, endKey) {
  const startCol = startKey[0];
  const startRow = parseInt(startKey.slice(1));
  const endCol = endKey[0];
  const endRow = parseInt(endKey.slice(1));

  let total = 0;
  for (let r = startRow; r <= endRow; r++) {
    const key = `${startCol}${r}`;
    const num = parseFloat(sheetCellData[key] || 0);
    if (!isNaN(num)) total += num;
  }
  return total;
}

function recalculateAllSheet() {
  document.querySelectorAll('.grid-cell').forEach(cell => {
    const key = cell.getAttribute('data-cell');
    if (sheetCellData[key] !== undefined) {
      cell.textContent = evaluateFormula(sheetCellData[key]);
    }
  });
  triggerDocChange();
}

function sheetFormat(type) {
  const cell = document.querySelector(`.grid-cell[data-cell="${AppState.activeCell}"]`);
  if (!cell) return;

  if (type === 'bold') {
    cell.style.fontWeight = cell.style.fontWeight === 'bold' ? 'normal' : 'bold';
  } else if (type === 'italic') {
    cell.style.fontStyle = cell.style.fontStyle === 'italic' ? 'normal' : 'italic';
  } else if (type === 'currency') {
    const num = parseFloat(cell.textContent.replace(/[^0-9.-]+/g, ""));
    if (!isNaN(num)) cell.textContent = `$${num.toLocaleString()}`;
  } else if (type === 'percent') {
    const num = parseFloat(cell.textContent);
    if (!isNaN(num)) cell.textContent = `${num}%`;
  }
  showToast(`${AppState.activeCell} katagi formatlandi`);
}

function insertSheetRow() {
  showToast("+ Yangi qator muvaffaqiyatli qo'shildi");
}

function insertSheetCol() {
  showToast("+ Yangi ustun qo'shildi");
}

function calculateAutoSum() {
  const col = AppState.activeCell[0];
  const row = parseInt(AppState.activeCell.slice(1));
  if (row > 1) {
    sheetCellData[AppState.activeCell] = `=SUM(${col}1:${col}${row - 1})`;
    recalculateAllSheet();
    showToast(`Avto-SUM hisoblandi: =SUM(${col}1:${col}${row - 1})`);
  }
}

function updateSheetSelectionSum(cellKey) {
  const sumEl = document.getElementById('selected-cells-sum');
  if (sumEl) {
    const num = parseFloat(sheetCellData[cellKey] || 0);
    sumEl.textContent = isNaN(num) ? '0' : num.toLocaleString();
  }
}

/* ==========================================================================
   4. PDF VIEWER & ANNOTATIONS (reja.txt 18-bo'lim)
   ========================================================================== */
let pdfCanvas, pdfCtx;
let currentTool = 'select';

function initPdfCanvas() {
  pdfCanvas = document.getElementById('pdf-annotation-canvas');
  if (!pdfCanvas) return;
  pdfCtx = pdfCanvas.getContext('2d');

  // Resize canvas to match sheet wrapper
  function resizeCanvas() {
    const rect = pdfCanvas.parentElement.getBoundingClientRect();
    pdfCanvas.width = rect.width;
    pdfCanvas.height = rect.height;
  }
  resizeCanvas();
  window.addEventListener('resize', resizeCanvas);

  // Tool buttons listener
  document.querySelectorAll('.annot-tool-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.annot-tool-btn').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      currentTool = btn.getAttribute('data-tool');
      showToast(`Tanlangan asbob: ${currentTool.toUpperCase()}`);
    });
  });

  // Canvas Drawing
  let lastX = 0, lastY = 0;

  pdfCanvas.addEventListener('mousedown', (e) => {
    if (currentTool === 'select') return;
    AppState.isDrawing = true;
    const rect = pdfCanvas.getBoundingClientRect();
    lastX = e.clientX - rect.left;
    lastY = e.clientY - rect.top;

    if (currentTool === 'note') {
      addPdfNote(lastX, lastY);
      AppState.isDrawing = false;
    }
  });

  pdfCanvas.addEventListener('mousemove', (e) => {
    if (!AppState.isDrawing || currentTool === 'select') return;
    const rect = pdfCanvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    pdfCtx.beginPath();
    pdfCtx.moveTo(lastX, lastY);
    pdfCtx.lineTo(x, y);

    if (currentTool === 'highlight') {
      pdfCtx.strokeStyle = 'rgba(254, 240, 138, 0.4)';
      pdfCtx.lineWidth = 18;
      pdfCtx.lineCap = 'square';
    } else if (currentTool === 'pen') {
      pdfCtx.strokeStyle = '#ef4444';
      pdfCtx.lineWidth = 2.5;
      pdfCtx.lineCap = 'round';
    }

    pdfCtx.stroke();
    lastX = x;
    lastY = y;
    triggerDocChange();
  });

  window.addEventListener('mouseup', () => {
    AppState.isDrawing = false;
  });

  // Signature Click Simulation
  const sigBox = document.getElementById('pdf-sig-box');
  if (sigBox) {
    sigBox.addEventListener('click', () => {
      const stamp = sigBox.querySelector('.sig-stamp');
      const placeholder = sigBox.querySelector('.sig-placeholder');
      if (stamp) {
        stamp.style.display = 'block';
        if (placeholder) placeholder.style.opacity = '0.3';
        showToast("Elektron imzo muvaffaqiyatli qo'yildi!", "success");
        triggerDocChange();
      }
    });
  }

  // Zoom Simulation
  let zoomLevel = 100;
  const zoomVal = document.getElementById('pdf-zoom-val');
  const sheet = document.getElementById('pdf-sheet');

  document.getElementById('btn-pdf-zoom-in')?.addEventListener('click', () => {
    if (zoomLevel < 150) {
      zoomLevel += 10;
      updateZoom();
    }
  });

  document.getElementById('btn-pdf-zoom-out')?.addEventListener('click', () => {
    if (zoomLevel > 70) {
      zoomLevel -= 10;
      updateZoom();
    }
  });

  function updateZoom() {
    if (zoomVal) zoomVal.textContent = `${zoomLevel}%`;
    if (sheet) sheet.style.transform = `scale(${zoomLevel / 100})`;
  }
}

function addPdfNote(x, y) {
  const noteText = prompt("PDF sahifasiga izoh kiriting:");
  if (noteText && pdfCtx) {
    pdfCtx.fillStyle = '#fef08a';
    pdfCtx.fillRect(x, y - 24, noteText.length * 9 + 16, 26);
    pdfCtx.fillStyle = '#854d0e';
    pdfCtx.font = '12px "Plus Jakarta Sans"';
    pdfCtx.fillText(`📌 ${noteText}`, x + 6, y - 8);
    showToast("Izoh saqlandi");
  }
}

/* ==========================================================================
   5. AUTOSAVE & CRASH RECOVERY (reja.txt 20-21 bo'limlar)
   ========================================================================== */
let autosaveTimeout = null;

function initAutosave() {
  const editableDoc = document.getElementById('docx-editable-page');
  if (editableDoc) {
    editableDoc.addEventListener('input', () => {
      triggerDocChange();
    });
  }

  // Check for crash recovery mock on startup
  if (Math.random() < 0.3) {
    setTimeout(() => {
      document.getElementById('recovery-modal')?.classList.add('show');
    }, 1200);
  }

  // Global Save Button
  document.getElementById('global-save-btn')?.addEventListener('click', () => {
    saveDocumentNow();
  });
}

function triggerDocChange() {
  if (saveStatusEl) {
    saveStatusEl.innerHTML = '<span class="status-dot" style="background-color: var(--warning-color)"></span> O\'zgarishlar kiritilmoqda...';
  }

  // 2–5 sec debounce as in reja.txt
  clearTimeout(autosaveTimeout);
  autosaveTimeout = setTimeout(() => {
    saveDocumentNow();
  }, 2500);
}

function saveDocumentNow() {
  if (saveStatusEl) {
    saveStatusEl.innerHTML = '<span class="status-dot green"></span> Saqlandi (Autosave faol)';
  }
  showToast("Hujjat avtomatik saqlandi (SAF Uri cache)");
}

function dismissRecovery() {
  document.getElementById('recovery-modal')?.classList.remove('show');
  showToast("Qoralama bekor qilindi");
}

function restoreRecovery() {
  document.getElementById('recovery-modal')?.classList.remove('show');
  showToast("Hujjat holati muvaffaqiyatli tiklandi!", "success");
}

/* ==========================================================================
   6. STORAGE ACCESS FRAMEWORK SIMULATOR (reja.txt 7-bo'lim)
   ========================================================================== */
function simulateSafAction(intentAction) {
  if (intentAction === 'ACTION_OPEN_DOCUMENT') {
    showToast("SAF Intent: ACTION_OPEN_DOCUMENT (Fayllar ro'yxati ochildi)");
    setTimeout(() => switchTab('screen-docx'), 800);
  } else if (intentAction === 'ACTION_CREATE_DOCUMENT') {
    const docName = prompt("Yangi hujjat nomini kiriting:", "Yangi_Hujjat.docx");
    if (docName) {
      showToast(`SAF: ${docName} uchun URI ruxsati berildi`);
      updateDocHeader(docName, 'DOCX', 'var(--docx-blue)');
      switchTab('screen-docx');
    }
  } else if (intentAction === 'ACTION_OPEN_DOCUMENT_TREE') {
    showToast("SAF: Jild uchun doimiy takePersistableUriPermission olindi");
  }
}

/* ==========================================================================
   7. THEME & DEVICE SIMULATOR TOGGLE
   ========================================================================== */
function initThemeAndDeviceControls() {
  // Theme Switcher
  const themeToggleBtn = document.getElementById('theme-toggle');
  if (themeToggleBtn) {
    themeToggleBtn.addEventListener('click', () => {
      document.body.classList.toggle('theme-light');
      const isLight = document.body.classList.contains('theme-light');
      themeToggleBtn.querySelector('.theme-icon').textContent = isLight ? '☀️' : '🌙';
      themeToggleBtn.querySelector('.theme-text').textContent = isLight ? 'Kunduzgi rejim' : 'Tungi rejim';
      showToast(isLight ? "Kunduzgi rejim yoqildi" : "Tungi rejim yoqildi");
    });
  }

  // Device Mode Switcher (Mobile vs Tablet/Desktop)
  const btnMobile = document.getElementById('btn-device-mobile');
  const btnTablet = document.getElementById('btn-device-tablet');

  btnMobile?.addEventListener('click', () => {
    btnMobile.classList.add('active');
    btnTablet.classList.remove('active');
    deviceWrapper.classList.add('mode-mobile');
    showToast("Mobil telefon ko'rinishi (WPS Compact Layout)");
  });

  btnTablet?.addEventListener('click', () => {
    btnTablet.classList.add('active');
    btnMobile.classList.remove('active');
    deviceWrapper.classList.remove('mode-mobile');
    showToast("Planshet / Katta ekran rejimi");
  });

  // Recent files filtering
  window.filterRecent = function(category, element) {
    document.querySelectorAll('.filter-pills .f-pill').forEach(p => p.classList.remove('active'));
    element.classList.add('active');

    const items = document.querySelectorAll('.file-card-item');
    items.forEach(item => {
      if (category === 'all') {
        item.style.display = 'flex';
      } else {
        item.style.display = item.classList.contains(`${category}-item`) ? 'flex' : 'none';
      }
    });
  };
}

/* ==========================================================================
   8. UTILITY & TOAST NOTIFICATIONS
   ========================================================================== */
function showToast(message, type = 'info') {
  const toast = document.createElement('div');
  toast.className = 'toast';
  
  const icon = type === 'success' ? '✅' : 'ℹ️';
  toast.innerHTML = `<span>${icon}</span> <span>${message}</span>`;
  
  toastContainer.appendChild(toast);
  setTimeout(() => {
    toast.style.opacity = '0';
    toast.style.transform = 'translateY(10px)';
    toast.style.transition = 'all 0.3s ease';
    setTimeout(() => toast.remove(), 300);
  }, 2800);
}

function initKeyboardShortcuts() {
  window.addEventListener('keydown', (e) => {
    if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 's') {
      e.preventDefault();
      saveDocumentNow();
    }
    if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 'b') {
      if (AppState.currentScreen === 'screen-docx') {
        e.preventDefault();
        applyDocStyle('bold');
      }
    }
    if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 'z') {
      e.preventDefault();
      showToast("Undo: Oxirgi amal bekor qilindi");
    }
  });

  document.getElementById('global-undo-btn')?.addEventListener('click', () => {
    showToast("Undo: Bekor qilindi");
  });
  document.getElementById('global-redo-btn')?.addEventListener('click', () => {
    showToast("Redo: Qaytarildi");
  });
  document.getElementById('btn-share-doc')?.addEventListener('click', () => {
    showToast("Android ACTION_SEND Intent chaqirildi (Telegram, Gmail)");
  });
}
