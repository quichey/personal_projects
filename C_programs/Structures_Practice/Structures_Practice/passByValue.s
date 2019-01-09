	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 13
	.globl	_doThis                 ## -- Begin function doThis
	.p2align	4, 0x90
_doThis:                                ## @doThis
	.cfi_startproc
## BB#0:
	pushq	%rbp
Lcfi0:
	.cfi_def_cfa_offset 16
Lcfi1:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Lcfi2:
	.cfi_def_cfa_register %rbp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rdi
	movl	$10, (%rdi)
	movq	-8(%rbp), %rdi
	movl	$12, 4(%rdi)
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.section	__TEXT,__literal8,8byte_literals
	.p2align	3               ## -- Begin function main
LCPI1_0:
	.quad	4613937818241073152     ## double 3
LCPI1_1:
	.quad	4617315517961601024     ## double 5
LCPI1_2:
	.quad	4621819117588971520     ## double 10
	.section	__TEXT,__text,regular,pure_instructions
	.globl	_main
	.p2align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## BB#0:
	pushq	%rbp
Lcfi3:
	.cfi_def_cfa_offset 16
Lcfi4:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Lcfi5:
	.cfi_def_cfa_register %rbp
	subq	$48, %rsp
	leaq	-16(%rbp), %rdi
	movl	$0, -4(%rbp)
	movl	$5, -16(%rbp)
	movl	$6, -12(%rbp)
	callq	_doThis
	leaq	L_.str(%rip), %rdi
	movl	-16(%rbp), %esi
	movb	$0, %al
	callq	_printf
	leaq	L_.str.1(%rip), %rdi
	movsd	LCPI1_2(%rip), %xmm0    ## xmm0 = mem[0],zero
	movl	%eax, -24(%rbp)         ## 4-byte Spill
	movb	$1, %al
	callq	_printf
	leaq	L_.str.1(%rip), %rdi
	movsd	LCPI1_1(%rip), %xmm0    ## xmm0 = mem[0],zero
	movl	%eax, -28(%rbp)         ## 4-byte Spill
	movb	$1, %al
	callq	_printf
	leaq	L_.str.1(%rip), %rdi
	movsd	LCPI1_0(%rip), %xmm0    ## xmm0 = mem[0],zero
	movl	%eax, -32(%rbp)         ## 4-byte Spill
	movb	$1, %al
	callq	_printf
	leaq	L_.str.2(%rip), %rdi
	leaq	-20(%rbp), %rsi
	movl	%eax, -36(%rbp)         ## 4-byte Spill
	movb	$0, %al
	callq	_printf
	leaq	L_.str(%rip), %rdi
	movl	_one(%rip), %esi
	movl	%eax, -40(%rbp)         ## 4-byte Spill
	movb	$0, %al
	callq	_printf
	xorl	%esi, %esi
	movl	%eax, -44(%rbp)         ## 4-byte Spill
	movl	%esi, %eax
	addq	$48, %rsp
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.section	__DATA,__data
	.globl	_x                      ## @x
	.p2align	2
_x:
	.long	10                      ## 0xa

	.globl	_y                      ## @y
.zerofill __DATA,__common,_y,4,2
	.globl	_one                    ## @one
	.p2align	2
_one:
	.long	4                       ## 0x4
	.long	5                       ## 0x5

	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"%d\n"

L_.str.1:                               ## @.str.1
	.asciz	"%f\n"

L_.str.2:                               ## @.str.2
	.asciz	"%p\n"


.subsections_via_symbols
