syntax on

" set settings here "
set nocompatible
filetype on
filetype plugin on
filetype indent on
set linebreak
set laststatus=2
set clipboard=unnamedplus
set backspace=indent,eol,start
set showcmd
set nu
set visualbell
set scrolloff=1
set wrapmargin=3
set ruler
set whichwrap=b,s,h,l,<,>,[,]
set nojoinspaces
set comments=sr:/*,mb:*,el:*/,://,b:#,:%,:XCOMM,n:>,fb:-,n:\:
set viminfo='100,<50,s10,h
set matchpairs+=<:>
set clipboard=autoselect,exclude:.*
set formatoptions+=r
set tabstop=4 softtabstop=4
set shiftwidth=4
set expandtab
set ignorecase
set smartcase
set termguicolors
set expandtab
set autoindent
set mouse=a
set smartindent
set noswapfile
set shell=bash
set renderoptions=type:directx
set encoding=utf-8
set signcolumn=yes
set incsearch
set smartcase
set noshowmode
set showtabline=4
set wildmode=longest,list,full
set scrolloff=8
set background=dark

set colorcolumn=80
highlight ColorColumn ctermbg=0 guibg=lightgrey

colorscheme gruvbox

" Plugins Start Here "
call plug#begin('~/vimfiles/plugged')

Plug 'scrooloose/nerdtree'
Plug 'itchyny/lightline.vim'
Plug 'tpope/vim-fugitive'
Plug 'vimwiki/vimwiki'
Plug 'raimondi/delimitmate'
Plug 'frazrepo/vim-rainbow'
Plug 'nathanaelkane/vim-indent-guides'
Plug 'mbbill/undotree'
Plug 'kien/ctrlp.vim'
Plug 'jremmen/vim-ripgrep'
Plug 'vim-airline/vim-airline'
Plug 'vim-airline/vim-airline-themes'
Plug 'flazz/vim-colorschemes'

call plug#end()


let mapleader = " "
let g:ctrlp_user_command = ['.git/', 'git --git-dir=%s.git ls-files -oc --exclude-standard']
let g:ctrlp_use_caching = 0
let g:netrw_winsize = 25
let g:netrw_banner = 0
let g:indent_guides_enable_on_vim_startup = 1
let g:indent_guides_auto_colors = 0
let g:rainbow_active = 1
let g:rainbow_guifgs = ['violet','CadetBlue','DarkOrange1','IndianRed2']

autocmd VimEnter,Colorscheme * :hi IndentGuidesOdd  guibg=#363725 ctermbg=3
autocmd VimEnter,Colorscheme * :hi IndentGuidesEven guibg=#2d3729 ctermbg=4

nnoremap <leader>h :wincmd h<CR>
nnoremap <leader>j :wincmd j<CR>
nnoremap <leader>k :wincmd k<CR>
nnoremap <leader>l :wincmd l<CR>
nnoremap <leader>u :UndotreeShow<CR>

"Auto Trim any trailing whitespace after saving file
fun! TrimWhiteSpace()
    let l:save = winsaveview()
    keeppatterns %s/\s\+$//e
    call winrestview(l:save)
endfun

augroup BRENDAN
    autocmd!
    autocmd BufWritePre * :call TrimWhiteSpace()
augroup END

"Airline Vim settings"
let g:airline#extenstions#tabline#enabled = 1
let g:airline#extenstions#tabline#left_sep = ''
let g:airline#extenstions#tabline#left_alt_sep = ''
let g:airline#extenstions#tabline#right_sep = ''
let g:airline#extenstions#tabline#right_alt_sep = ''
"Switch Airline to current theme"
let g:airline_theme = "base16_monokai"

"Debug and compile Java Files
"Can only find compile time errors
"You can scroll through thr errors in the seperate terminal
"Press F9 to compile and check for errors
autocmd Filetype java set makeprg=javac\ %
set errorformat=%A%f:%l:\ %m,%-Z%p^,%-C%.%#
map <F9> :make<Return>:copen<Return>
map <F10> :cprevious<Return>
map <F12> :cnext<Return>

"Auto Javac and Java Script
"Press F5 to compile and run
map <F5> :call CompileRunGcc()<CR>
func! CompileRunGcc()
    exec "w"
    if &filetype == 'c'
        exec "!g++ % -o %<"
        exec "!time ./%<"
    elseif &filetype == 'cpp'
        exec "!g++ % -o %<"
        exec "!time ./%<"
    elseif &filetype == 'java'
        exec "!javac %"
        exec "!time java %<"
    elseif &filetype == 'sh'
        :!time bash %
    elseif &filetype == 'python'
        exec "!time python3.9 %"
    elseif &filetype == 'html'
        exec "!firefox % &"
    elseif &filetype == 'go'
        exec "!go build %<"
        exec "!time go run %"
    elseif &filetype == 'mkd'
        exec "!~/.vim/markdown.pl % > %.html &"
        exec "!firefox %.html &"
    endif
endfunc


if has("autocmd")
    au VimEnter,InsertLeave * silent execute '!echo -ne "\e[2 q"' | redraw!
      au InsertEnter,InsertChange *
            \ if v:insertmode == 'i' |
            \   silent execute '!echo -ne "\e[6 q"' | redraw! |
            \ elseif v:insertmode == 'r' |
            \   silent execute '!echo -ne "\e[4 q"' | redraw! |
            \ endif
      au VimLeave * silent execute '!echo -ne "\e[ q"' | redraw!
endif

let java_highlight_functions = 1
let java_highlight_all = 1
" If you are trying this at runtime, you need to reload the syntax file
" set filetype=java

" Some more highlights, in addition to those suggested by cmcginty
highlight link javaScopeDecl Statement
highlight link javaType Type
highlight link javaDocTags PreProc

" WSL yank support
let s:clip = '/mnt/c/Windows/System32/clip.exe'  " change this path according to your mount point
if executable(s:clip)
    augroup WSLYank
        autocmd!
        autocmd TextYankPost * if v:event.operator ==# 'y' | call system(s:clip, @0) | endif
    augroup END
endif
